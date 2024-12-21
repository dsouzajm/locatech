package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.dtos.AluguelRequestDTO;
import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.services.AluguelService;
import br.com.fiap.locatech.locatech.services.AluguelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/alugueis")
public class AluguelController {
    private static final Logger logger = (Logger) Logger.getLogger(AluguelController.class.getName());
    private final AluguelService aluguelService;
    public AluguelController(AluguelService aluguelService){
        this.aluguelService = aluguelService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Aluguel>> findAlugel(@PathVariable Long id){
        logger.info("GET /alugueis/{id}");
        return ResponseEntity.ok(this.aluguelService.findAluguelById(id));
    }
    @GetMapping
    public ResponseEntity<List<Aluguel>> findAlugueis(@RequestParam int page, @RequestParam int size){
        logger.info("GET /alugueis");
        return ResponseEntity.ok(this.aluguelService.findAllAlugueis(page, size));
    }
    @PostMapping
    public ResponseEntity<Void> saveAluguel(@Valid @RequestBody AluguelRequestDTO aluguelRequestDTO){
        logger.info("POST /alugueis");
        this.aluguelService.saveAluguel(aluguelRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAluguel(@PathVariable Long id, @RequestBody Aluguel pessoa){
        logger.info("PUT /alugueis/" + id);
        this.aluguelService.updateAluguel(pessoa, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluguel(@PathVariable Long id){
        logger.info("DELETE /alugueis/" + id);
        this.aluguelService.deleteAluguel(id);
        return ResponseEntity.ok().build();
    }
}
