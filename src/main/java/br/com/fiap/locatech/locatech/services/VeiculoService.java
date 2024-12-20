package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.entities.Veiculo;
import br.com.fiap.locatech.locatech.excepetions.ResourceNotFoundException;
import br.com.fiap.locatech.locatech.repositories.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;
    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }
    public Optional<Veiculo> findVeiculoById(Long id){
        return this.veiculoRepository.findById(id);
    }
    public List<Veiculo> findAllVeiculos(int page, int size){
        int offSet = (page - 1) * size;
        return this.veiculoRepository.findAll(offSet, size);
    }
    public void saveVeiculo(Veiculo veiculo){
        Integer save = this.veiculoRepository.save(veiculo);
        Assert.state(save == 1, "Erro ao salvar veículo " + veiculo.getModelo());
    }
    public void updateVeiculo(Veiculo veiculo, Long id){
        Integer update = this.veiculoRepository.update(veiculo, id);
        if(update == 0){
            throw new ResourceNotFoundException("Veículo não encontrado");
        }
    }
    public void deleteVeiculo(Long id){
        Integer delete = this.veiculoRepository.delete(id);
        if(delete == 0) {
            throw new ResourceNotFoundException("Veículo não encontrado");
        }
    }
}
