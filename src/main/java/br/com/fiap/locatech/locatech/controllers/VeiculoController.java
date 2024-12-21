package br.com.fiap.locatech.locatech.controllers;

import br.com.fiap.locatech.locatech.entities.Veiculo;
import br.com.fiap.locatech.locatech.services.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/veiculos")
@Tag(name = "Veículo", description = "Controller para CRUD de veículos")
public class VeiculoController {

    private static final Logger logger = (Logger) Logger.getLogger(VeiculoController.class.getName());
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService){
        this.veiculoService = veiculoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Veiculo>> findVeiculo(@PathVariable Long id){
        logger.info("GET /veiculos/{id}");
        return ResponseEntity.ok(this.veiculoService.findVeiculoById(id));
    }

    @Operation(
        description = "Busca todos os veículos de forma paginada",
        summary = "Busca de veículos",
        responses = {
            @ApiResponse(description = "OK", responseCode = "200")
        }
    )
    @GetMapping
    public ResponseEntity<List<Veiculo>> findVeiculos(@RequestParam int page, @RequestParam int size){
        logger.info("GET /veiculos");
        return ResponseEntity.ok(this.veiculoService.findAllVeiculos(page, size));
    }

    @PostMapping
    public ResponseEntity<Void> saveVeiculo(@RequestBody Veiculo veiculo){
        logger.info("POST /veiculos");
        this.veiculoService.saveVeiculo(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo){
        logger.info("PUT /veiculos/" + id);
        this.veiculoService.updateVeiculo(veiculo, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id){
        logger.info("DELETE /veiculos/" + id);
        this.veiculoService.deleteVeiculo(id);
        return ResponseEntity.ok().build();
    }
}
