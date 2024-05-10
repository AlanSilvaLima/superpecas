package br.com.masterclass.superpecas.controller;

import br.com.masterclass.superpecas.model.Peca;
import br.com.masterclass.superpecas.service.PecaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/peca")
public class PecaController {
    private final PecaService pecaService;


    public PecaController(PecaService pecaService) {
        this.pecaService = pecaService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Peca> getPecaById(@PathVariable int id) {
        Optional<Peca> peca = pecaService.findById(id);
        return peca.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Peca> addPeca(@Valid @RequestBody Peca peca) {
        Peca newPeca = pecaService.save(peca);
        return new ResponseEntity<>(newPeca, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Peca> updatePeca(@Valid @RequestBody Peca peca) {
        if (pecaService.findById(peca.getPecaID()).isPresent()) {
            Peca updatedPeca = pecaService.save(peca);
            return ResponseEntity.ok().body(updatedPeca);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeca(@PathVariable int id) {
        if (pecaService.findById(id).isPresent()) {
            pecaService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listaTodosPaginado")
    public ResponseEntity<List<Peca>> listTodosPaginado(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        List<Peca> pecas = pecaService.listaTodosPaginado(page, size);
        return ResponseEntity.ok().body(pecas);
    }
    @GetMapping("/listaTodosPaginado/{termo}")
    public ResponseEntity<List<Peca>> listTodosPaginado(@RequestParam(required = false) String termo,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
        List<Peca> pecas = pecaService.listaTodosPaginado(termo, page, size);
        return ResponseEntity.ok().body(pecas);
    }
    @GetMapping("/listaTop10CarroComMaisPecas")
    public ResponseEntity<List<Object[]>> listaTop10CarroComMaisPecas() {
        List<Object[]> top10CarrosComMaisPecas = pecaService.listaTop10CarroComMaisPecas();
        return ResponseEntity.ok().body(top10CarrosComMaisPecas);
    }
}