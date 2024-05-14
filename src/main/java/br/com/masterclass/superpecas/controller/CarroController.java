package br.com.masterclass.superpecas.controller;

import br.com.masterclass.superpecas.DTO.CarroDTO;
import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Lista todos os carros",
            description = "Retorna uma lista de todos os carros cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de carros encontrada",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Carro.class))) })
    @GetMapping("/listaTodos")
    public ResponseEntity<List<Carro>> listaTodosCarros() {
        List<Carro> carros = carroService.findAll();
        if (!carros.isEmpty()) {
            return ResponseEntity.ok(carros);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "Buscar carro por ID",
            description = "Busca um carro pelo seu ID.")
    @ApiResponse(responseCode = "200", description = "Carro encontrado",
            content = { @Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Carro.class)) })
    @GetMapping("/{carroID}")
    public Carro findById(@PathVariable int carroID) {
        return carroService.findById(carroID);
    }

    @Operation(summary = "Cria um novo carro",
            description = "Cria um novo registro de carro.")
    @ApiResponse(responseCode = "201", description = "Carro criado",
            content = { @Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Carro.class)) })
    @PostMapping
    public ResponseEntity<Carro> criarCarro(@Valid @RequestBody Carro novoCarro) {
        Carro carroCriado = carroService.save(novoCarro);
        return ResponseEntity.status(HttpStatus.CREATED).body(carroCriado);
    }

    @Operation(summary = "Atualiza um carro",
            description = "Atualiza um registro de carro existente.")
    @ApiResponse(responseCode = "200", description = "Carro atualizado",
            content = { @Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Carro.class)) })
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

    @Operation(summary = "Exclui um carro",
            description = "Exclui um registro de carro existente.")
    @ApiResponse(responseCode = "204", description = "Carro excluído")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        carroService.deleteById(id);
    }

    @Operation(summary = "Lista todos os fabricantes de carros",
            description = "Retorna uma lista de todos os fabricantes de carros cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de fabricantes encontrada",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @io.swagger.v3.oas.annotations.media.Schema(type = "string"))) })
    @GetMapping("/listaTodosFabricantes")
    public ResponseEntity<List<String>> listaTodosFabricantes() {
        List<String> fabricantes = carroService.listaTodosFabricantes();
        return ResponseEntity.ok().body(fabricantes);
    }

    @Operation(summary = "Lista todos os carros paginados",
            description = "Retorna uma lista paginada de todos os carros cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de carros paginada encontrada",
            content = { @Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Page.class)) })
    @GetMapping("/listaTodosPaginado")
    public ResponseEntity<Page<CarroDTO>> listaTodosPaginadoDTO(@Parameter(description = "Número da página") @RequestParam(defaultValue = "0") int page,
                                                                @Parameter(description = "Número de registros por página") @RequestParam(defaultValue = "10") int size,
                                                                Pageable pageable) {
        Page<CarroDTO> carros = carroService.listaTodosPaginadoDTO(pageable);
        return ResponseEntity.ok().body(carros);
    }

    @Operation(summary = "Lista todos os carros paginados com termo de busca",
            description = "Retorna uma lista paginada de todos os carros cadastrados, filtrados por um termo de busca.")
    @ApiResponse(responseCode = "200", description = "Lista de carros paginada com termo de busca encontrada",
            content = { @Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Page.class)) })
    @GetMapping("/listaTodosPaginado/{termo}")
    public ResponseEntity<Page<CarroDTO>> listaTodosPaginadoDTO(@Parameter(description = "Número da página") @RequestParam(defaultValue = "0") int page,
                                                                @Parameter(description = "Número de registros por página") @RequestParam(defaultValue = "10") int size,
                                                                @Parameter(description = "Termo de busca") @PathVariable(required = false) String termo,
                                                                Pageable pageable) {
        Page<CarroDTO> carros = carroService.listaTodosPaginadoDTO(termo, pageable);
        return ResponseEntity.ok().body(carros);
    }
    @Operation(summary = "Lista os top 10 fabricantes de carros",
            description = "Retorna uma lista dos 10 principais fabricantes de carros com base na contagem.")
    @ApiResponse(responseCode = "200", description = "Lista dos top 10 fabricantes de carros encontrada",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @io.swagger.v3.oas.annotations.media.Schema(type = "string"))) })
    @GetMapping("/listaTop10Fabricantes")
    public ResponseEntity<List<String>> listaTop10Fabricantes(@Parameter(description = "Número da página") @RequestParam(defaultValue = "0") int page,
                                                              @Parameter(description = "Número de registros por página") @RequestParam(defaultValue = "10") int size) {
        Page<Object[]> fabricantesPage = carroService.listaTop10Fabricantes(page, size);

        List<String> fabricantes = fabricantesPage.getContent()
                .stream()
                .map(array -> (String) array[0])
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(fabricantes);
    }
}
