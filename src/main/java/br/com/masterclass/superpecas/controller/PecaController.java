package br.com.masterclass.superpecas.controller;

import br.com.masterclass.superpecas.DTO.PecaDTO;
import br.com.masterclass.superpecas.model.Peca;
import br.com.masterclass.superpecas.service.PecaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Operation(summary = "Buscar peça por ID",
            description = "Busca uma peça pelo seu ID.")
    @ApiResponse(responseCode = "200", description = "Peça encontrada",
            content = { @Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Peca.class)) })
    @GetMapping("/{id}")
    public ResponseEntity<Peca> getPecaById(@Parameter(description = "ID da peça a ser buscada", example = "123") @PathVariable int id) {
        Optional<Peca> peca = pecaService.findById(id);
        return peca.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Adicionar uma nova peça",
            description = "Adiciona uma nova peça ao registro.")
    @ApiResponse(responseCode = "201", description = "Peça adicionada",
            content = { @Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Peca.class)) })
    @PostMapping
    public ResponseEntity<Peca> addPeca(@Valid @RequestBody Peca peca) {
        Peca newPeca = pecaService.save(peca);
        return new ResponseEntity<>(newPeca, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar uma peça",
            description = "Atualiza uma peça existente.")
    @ApiResponse(responseCode = "200", description = "Peça atualizada",
            content = { @Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Peca.class)) })
    @PutMapping
    public ResponseEntity<Peca> updatePeca(@Valid @RequestBody Peca peca) {
        if (pecaService.findById(peca.getPecaID()).isPresent()) {
            Peca updatedPeca = pecaService.save(peca);
            return ResponseEntity.ok().body(updatedPeca);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir uma peça",
            description = "Exclui uma peça existente.")
    @ApiResponse(responseCode = "204", description = "Peça excluída")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeca(@Parameter(description = "ID da peça a ser excluída", example = "123") @PathVariable int id) {
        if (pecaService.findById(id).isPresent()) {
            pecaService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Listar todas as peças paginadas",
            description = "Retorna uma lista paginada de todas as peças cadastradas.")
    @ApiResponse(responseCode = "200", description = "Lista de peças paginada encontrada",
            content = { @Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Page.class)) })
    @GetMapping("/listaTodosPaginado")
    public ResponseEntity<Page<PecaDTO>> listTodosPaginadoDTO(@Parameter(description = "Número da página") @RequestParam(defaultValue = "0") int page,
                                                              @Parameter(description = "Número de registros por página") @RequestParam(defaultValue = "10") int size,
                                                              Pageable pageable) {
        Page<PecaDTO> pecas = pecaService.listaTodosPaginadoDTO(pageable);
        return ResponseEntity.ok().body(pecas);
    }

    @Operation(summary = "Listar todas as peças paginadas com termo de busca",
            description = "Retorna uma lista paginada de todas as peças cadastradas, filtradas por um termo de busca.")
    @ApiResponse(responseCode = "200", description = "Lista de peças paginada com termo de busca encontrada",
            content = { @Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Page.class)) })
    @GetMapping("/listaTodosPaginado/{termo}")
    public ResponseEntity<Page<PecaDTO>> listTodosPaginadoDTO(@Parameter(description = "Número da página") @RequestParam(defaultValue = "0") int page,
                                                              @Parameter(description = "Número de registros por página") @RequestParam(defaultValue = "10") int size,
                                                              @Parameter(description = "Termo de busca") @RequestParam(required = false) String termo,
                                                              Pageable pageable) {
        Page<PecaDTO> pecas = pecaService.listaTodosPaginadoDTO(termo, pageable);
        return ResponseEntity.ok().body(pecas);
    }
    @Operation(summary = "Listar os top 10 carros com mais peças",
            description = "Retorna uma lista dos 10 principais carros com a contagem máxima de peças.")
    @ApiResponse(responseCode = "200", description = "Lista dos top 10 carros com mais peças encontrada",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @io.swagger.v3.oas.annotations.media.Schema(type = "string"))) })
    @GetMapping("/listaTop10CarroComMaisPecas")
    public ResponseEntity<List<Object[]>> listaTop10CarroComMaisPecas() {
        List<Object[]> top10CarrosComMaisPecas = pecaService.listaTop10CarroComMaisPecas();
        return ResponseEntity.ok().body(top10CarrosComMaisPecas);
    }
}
