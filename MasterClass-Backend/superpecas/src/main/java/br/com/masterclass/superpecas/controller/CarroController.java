package br.com.masterclass.superpecas.controller;

import br.com.masterclass.superpecas.model.CarroModel;
import br.com.masterclass.superpecas.model.DTO.CarroDTO;
import br.com.masterclass.superpecas.model.DTO.TopFabricantesDTO;
import br.com.masterclass.superpecas.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    CarroService carroService;

    @Autowired
    ModelMapper modelMapper;

    @Operation(summary = "Busca carro", description = "Busca os dados de um carro pelo seu Id.")
    @ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CarroDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado", content = @Content) })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CarroDTO> buscaCarro(@Parameter(description = "Id do Carro", required = true) @PathVariable int id) {
       CarroModel carroModel = carroService.buscaCarro(id);

       if (carroModel == null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }

       return new ResponseEntity<>(modelMapper.map(carroModel, CarroDTO.class), HttpStatus.OK);
    }

    @Operation(summary = "Lista carros", description = "Lista todos os carros.")
    @ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CarroDTO[].class)) })})
    @RequestMapping(value = "/listaTodos", method = RequestMethod.GET)
    public ResponseEntity<List<CarroDTO>> listaCarros() {
        List<CarroModel> carros = carroService.listaCarros();
        List<CarroDTO> listaDTO = Arrays.asList(modelMapper.map(carros, CarroDTO[].class));

        return new ResponseEntity<>(listaDTO, HttpStatus.OK);
    }

    @Operation(summary = "Lista carros paginado", description = "Lista todos os carros por página.")
    @ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CarroDTO[].class)) })})
    @RequestMapping(value = "/listaTodosPaginado", method = RequestMethod.GET)
    public ResponseEntity<Page<CarroDTO>> listaCarros(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<CarroModel> carros = carroService.listaCarrosPaginado(paging);
        return new ResponseEntity<>(carros.map(carro -> modelMapper.map(carro, CarroDTO.class)), HttpStatus.OK);
    }

    @Operation(summary = "Lista carros por nome e/ou fabricante paginado", description = "Lista todos os carros por nome e/ou fabricante por página.")
    @ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CarroDTO[].class)) })})
    @RequestMapping(value = {"/listaTodosPaginado/{termo}"}, method = RequestMethod.GET)
    public ResponseEntity<Page<CarroDTO>> listaCarros(@PathVariable String termo, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<CarroModel> carros = carroService.listaCarrosPorNomeEOuFabricantePaginado(termo, paging);
        return new ResponseEntity<>(carros.map(carro -> modelMapper.map(carro, CarroDTO.class)), HttpStatus.OK);
    }

    @Operation(summary = "Lista fabricantes", description = "Lista todos os fabricantes.")
    @ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = String[].class)) })})
        @RequestMapping(value = "/listaTodosFabricantes", method = RequestMethod.GET)
    public ResponseEntity<List<String>> listaFabricantes() {
        List<String> fabricantes = carroService.listaFabricantes();
        return new ResponseEntity<>(fabricantes, HttpStatus.OK);
    }
    @Operation(summary = "Grava carro", description = "Grava um novo carro no sistema.")
    @ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CarroDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content) })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity gravaCarro(@Parameter(description = "Dados do Carro", required = true) @RequestBody CarroDTO data) {
        CarroModel carroModel = modelMapper.map(data, CarroModel.class);
        carroModel = carroService.gravaCarro(carroModel);

        if (carroModel == null){
            return new ResponseEntity<>("Erro ao gravar carro", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(modelMapper.map(carroModel, CarroDTO.class), HttpStatus.OK);
    }

    @Operation(summary = "Atualiza carro", description = "Atualiza dados de um carro existente no sistema.")
    @ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CarroDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content) })
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity editaCarro(@Parameter(description = "Dados do Carro", required = true) @RequestBody CarroDTO data) {
        CarroModel carroModel = modelMapper.map(data, CarroModel.class);
        carroModel = carroService.editaCarro(carroModel);

        if (carroModel == null){
            return new ResponseEntity<>("Erro ao atualizar carro", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(modelMapper.map(carroModel, CarroDTO.class), HttpStatus.OK);
    }

    @Operation(summary = "Excluir carro", description = "Exclui um carro do sistema.")
    @ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CarroDTO.class)) })})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity excluiCarro(@Parameter(description = "Id do Carro", required = true)  @PathVariable int id) {
        boolean retorno = carroService.excluiCarro(id);
        if (retorno)
            return new ResponseEntity<>(HttpStatus.OK);
        else {
            return new ResponseEntity<>("Carro possui peças, não é possível remover", HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Lista TOP 10 fabricantes", description = "Lista TOP 10 fabricantes.")
    @ApiResponses({ @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TopFabricantesDTO[].class)) })})
    @RequestMapping(value = "/listaTop10Fabricantes", method = RequestMethod.GET)
    public ResponseEntity<List<TopFabricantesDTO>> listaTop10Fabricantes() {
        List<TopFabricantesDTO> fabricantes = carroService.listaTop10Fabricantes();
        return new ResponseEntity<>(fabricantes, HttpStatus.OK);
    }
}
