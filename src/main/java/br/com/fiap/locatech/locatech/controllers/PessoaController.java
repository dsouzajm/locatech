package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.entities.Pessoa;
import br.com.fiap.locatech.locatech.entities.Veiculo;
import br.com.fiap.locatech.locatech.services.PessoaService;
import br.com.fiap.locatech.locatech.services.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/pessoas")
public class PessoaController {
    private static final Logger logger = (Logger) Logger.getLogger(PessoaController.class.getName());
    private final PessoaService pessoaService;
    public PessoaController(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pessoa>> findPessoa(@PathVariable Long id){
        logger.info("GET /pessoas/{id}");
        return ResponseEntity.ok(this.pessoaService.findPessoaById(id));
    }
    @GetMapping
    public ResponseEntity<List<Pessoa>> findPessoas(@RequestParam int page, @RequestParam int size){
        logger.info("GET /pessoas");
        return ResponseEntity.ok(this.pessoaService.findAllPessoas(page, size));
    }
    @PostMapping
    public ResponseEntity<Void> savePessoa(@RequestBody Pessoa pessoa){
        logger.info("POST /pessoas");
        this.pessoaService.savePessoa(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa){
        logger.info("PUT /pessoas/" + id);
        this.pessoaService.updatePessoa(pessoa, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id){
        logger.info("DELETE /pessoas/" + id);
        this.pessoaService.deletePessoa(id);
        return ResponseEntity.ok().build();
    }
}
