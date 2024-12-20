package br.com.fiap.locatech.locatech.repositories;

import br.com.fiap.locatech.locatech.entities.Pessoa;
import br.com.fiap.locatech.locatech.entities.Veiculo;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository {
    Optional<Pessoa> findById(Long id);
    List<Pessoa> findAll(int offset, int size);
    Integer save(Pessoa pessoa);
    Integer update(Pessoa pessoa, Long id);
    Integer delete(Long id);
}
