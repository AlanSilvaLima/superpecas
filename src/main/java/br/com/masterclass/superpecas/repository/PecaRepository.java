package br.com.masterclass.superpecas.repository;

import br.com.masterclass.superpecas.model.Peca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Integer> {

    Page<Peca> findAll(Pageable pageable);

    Page<Peca> findByNomeContainingIgnoreCase(String termo, Pageable pageable);

    @Query(value = "SELECT c.NomeModelo, COUNT(pc.PecaID) AS totalPecas " +
            "FROM Carros c " +
            "JOIN Pecas p ON c.CarroID = p.CarroID " +
            "GROUP BY c.CarroID " +
            "ORDER BY totalPecas DESC " +
            "LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10CarroComMaisPecas();
}
