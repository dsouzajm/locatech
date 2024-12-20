package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.entities.Pessoa;
import br.com.fiap.locatech.locatech.entities.Veiculo;
import br.com.fiap.locatech.locatech.excepetions.ResourceNotFoundException;
import br.com.fiap.locatech.locatech.repositories.PessoaRepository;
import br.com.fiap.locatech.locatech.repositories.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Optional<Pessoa> findPessoaById(Long id){
        return this.pessoaRepository.findById(id);
    }

    public List<Pessoa> findAllPessoas(int page, int size){
        int offSet = (page - 1) * size;
        return this.pessoaRepository.findAll(offSet, size);
    }

    public void savePessoa(Pessoa pessoa){
        Integer save = this.pessoaRepository.save(pessoa);
        Assert.state(save == 1, "Erro ao salvar pessoa " + pessoa.getNome());
    }

    public void updatePessoa(Pessoa pessoa, Long id){
        Integer update = this.pessoaRepository.update(pessoa, id);
        if(update == 0){
            throw new ResourceNotFoundException("Pessoa não encontrada");
        }
    }

    public void deletePessoa(Long id){
        Integer delete = this.pessoaRepository.delete(id);
        if(delete == 0) {
            throw new ResourceNotFoundException("Pessoa não encontrada");
        }
    }
}
