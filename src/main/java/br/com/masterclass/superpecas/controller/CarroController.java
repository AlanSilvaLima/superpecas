package br.com.masterclass.superpecas.controller;

import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.service.CarroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")


public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    //Metodo -ListaTodos
    @GetMapping("/listaTodos")
    public ResponseEntity<List<Carro>> listaTodosCarros() {
        List<Carro> carros = carroService.findAll();
        if (!carros.isEmpty()) {
            return ResponseEntity.ok(carros);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    //Metodo -Get por ID
    @GetMapping("/{carroID}")
    public Carro findById(@PathVariable int carroID) {
        return carroService.findById(carroID);
    }

    //Cria Carro
    @PostMapping
    public ResponseEntity<Carro> criarCarro(@Valid @RequestBody Carro novoCarro) {
        Carro carroCriado = carroService.save(novoCarro);
        return ResponseEntity.status(HttpStatus.CREATED).body(carroCriado);
    }

    //Atualiza Carros
    @PutMapping("/{carroID}")
    public ResponseEntity<?> update(@PathVariable int carroID, @RequestBody Carro novoCarro) {
        Carro carroExistente = carroService.findById(carroID);
        if (carroExistente != null) {
            novoCarro.setCarroID(carroExistente.getCarroID());
            Carro carroAtualizado = carroService.save(novoCarro);
            return ResponseEntity.ok(carroAtualizado);
        } else {
            return ResponseEntity.badRequest().body("O carro com o ID " + carroID + " não existe.");
        }
    }

    //Metodo Delete - nao deleta carro com chave estrangeira..
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        carroService.deleteById(id);
    }
}
