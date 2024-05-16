package br.com.masterclass.superpecas.repository;

import br.com.masterclass.superpecas.model.CarroModel;
import br.com.masterclass.superpecas.model.DTO.TopFabricantesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarroRepository extends CrudRepository<CarroModel, Integer> {

    public CarroModel findByNomeModelo(String nomeModelo);

    public CarroModel findByNomeModeloOrCodigoUnico(String nomeModelo, String codigoUnico);

    Page<CarroModel> findAll(Pageable pageable);

    @Query(value = "SELECT c FROM CarroModel c WHERE lower(c.nomeModelo) like lower(concat('%', ?1,'%')) OR (?1 IS NULL OR lower(c.fabricante) like lower(concat('%', ?1,'%')))")
    Page<CarroModel> findByNomeModeloEOuFabricante(String nome, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT c.fabricante FROM Carros c GROUP BY c.fabricante")
    List<String> findAllFabricantes();

    @Query(nativeQuery = true, value = "SELECT Count(CarroID) 'quantidade', fabricante FROM Carros GROUP BY Fabricante ORDER BY Count(CarroID) DESC LIMIT 10")
    List<TopFabricantesDTO> findTop10Fabricantes();

}
