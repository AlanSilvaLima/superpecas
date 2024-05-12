package br.com.masterclass.superpecas.controller;

import br.com.masterclass.superpecas.DTO.CarroDTO;
import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.service.CarroService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/listaTodosFabricantes")
    public ResponseEntity<List<String>> listaTodosFabricantes() {
        List<String> fabricantes = carroService.listaTodosFabricantes();
        return ResponseEntity.ok().body(fabricantes);
    }
    @GetMapping("/listaTodosPaginado")
    public ResponseEntity<Page<CarroDTO>> listaTodosPaginadoDTO(Pageable pageable) {
        Page<CarroDTO> carros = carroService.listaTodosPaginadoDTO(pageable);
        return ResponseEntity.ok().body(carros);
    }

    @GetMapping("/listaTodosPaginado/{termo}")
    public ResponseEntity<Page<CarroDTO>> listaTodosPaginadoDTO(@PathVariable(required = false) String termo, Pageable pageable) {
        Page<CarroDTO> carros = carroService.listaTodosPaginadoDTO(termo, pageable);
        return ResponseEntity.ok().body(carros);
    }
    @GetMapping("/listaTop10Fabricantes")
    public ResponseEntity<List<String>> listaTop10Fabricantes(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        Page<Object[]> fabricantesPage = carroService.listaTop10Fabricantes(page, size);

        List<String> fabricantes = fabricantesPage.getContent()
                .stream()
                .map(array -> (String) array[0])
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(fabricantes);
    }
}
