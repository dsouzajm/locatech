package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.dtos.AluguelRequestDTO;
import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.entities.Pessoa;
import br.com.fiap.locatech.locatech.entities.Veiculo;
import br.com.fiap.locatech.locatech.excepetions.ResourceNotFoundException;
import br.com.fiap.locatech.locatech.repositories.AluguelRepository;
import br.com.fiap.locatech.locatech.repositories.PessoaRepository;
import br.com.fiap.locatech.locatech.repositories.VeiculoRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {
    private final AluguelRepository aluguelRepository;
    private final VeiculoRepository veiculoRepository;
    private final PessoaRepository pessoaRepository;
    public AluguelService(AluguelRepository aluguelRepository,
                          VeiculoRepository veiculoRepository,
                          PessoaRepository pessoaRepository) {
        this.aluguelRepository = aluguelRepository;
        this.veiculoRepository = veiculoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public Optional<Aluguel> findAluguelById(Long id){
        return this.aluguelRepository.findById(id);
    }

    public List<Aluguel> findAllAlugueis(int page, int size){
        int offSet = (page - 1) * size;
        return this.aluguelRepository.findAll(offSet, size);
    }

    public void saveAluguel(AluguelRequestDTO aluguelRequestDTO){
        Integer save = this.aluguelRepository.save(calculaAluguel(aluguelRequestDTO));
        Assert.state(save == 1, "Erro ao salvar aluguel " + aluguelRequestDTO.pessoaId());
    }

    public void updateAluguel(Aluguel aluguel, Long id){
        Integer update = this.aluguelRepository.update(aluguel, id);
        if(update == 0){
            throw new ResourceNotFoundException("Aluguel não encontrado");
        }
    }

    public void deleteAluguel(Long id){
        Integer delete = this.aluguelRepository.delete(id);
        if(delete == 0) {
            throw new ResourceNotFoundException("Aluguel não encontrado");
        }
    }

    public Aluguel calculaAluguel(AluguelRequestDTO aluguelRequestDTO){
        Veiculo veiculo = veiculoRepository.findById(aluguelRequestDTO.veiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
        Pessoa pessoa = pessoaRepository.findById(aluguelRequestDTO.pessoaId())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));

        long quantidadeDiasAlocados = ChronoUnit.DAYS.between(aluguelRequestDTO.dataFim(), aluguelRequestDTO.dataInicio());
        BigDecimal valorTotalAluguel = veiculo.getValorDiaria().multiply(BigDecimal.valueOf(quantidadeDiasAlocados)).abs();

        return Aluguel.builder()
                .pessoaId(aluguelRequestDTO.pessoaId())
                .veiculoId(aluguelRequestDTO.veiculoId())
                .veiculoModelo(veiculo.getModelo())
                .pessoaCpf(pessoa.getCpf())
                .pessoaNome(pessoa.getNome())
                .dataInicio(aluguelRequestDTO.dataInicio())
                .dataFim(aluguelRequestDTO.dataFim())
                .valorTotal(valorTotalAluguel)
            .build();
    }
}
