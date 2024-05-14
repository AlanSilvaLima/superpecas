package br.com.masterclass.superpecas.repository;

import br.com.masterclass.superpecas.model.Peca;
import br.com.masterclass.superpecas.repository.projection.TopCarroProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Integer> {

    Page<Peca> findAll(Pageable pageable);

    Page<Peca> findByNomeContainingIgnoreCaseOrNumeroSerieContainingIgnoreCaseOrFabricanteContainingIgnoreCaseOrModeloCarroContainingIgnoreCase(String nome, String numeroSerie, String fabricante, String modeloCarro, Pageable pageable);


    @Query(value = "SELECT c.NomeModelo AS nomeModelo " +
            "FROM Carros c " +
            "JOIN Pecas p ON c.CarroID = p.CarroID " +
            "GROUP BY c.NomeModelo " +
            "ORDER BY COUNT(p.PecaID) DESC " +
            "LIMIT 10", nativeQuery = true)
    List<TopCarroProjection> findTop10CarroComMaisPecas();
}
