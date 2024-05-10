package br.com.masterclass.superpecas.repository;

import br.com.masterclass.superpecas.model.Carro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CarroRepository extends JpaRepository<Carro, Integer> {

    @Query("SELECT DISTINCT c.fabricante FROM Carro c")
    List<String> findAllFabricantes();
    Page<Carro> findAll(Pageable pageable);
    Page<Carro> findAllByNomeModeloContainingIgnoreCase(String termo, Pageable pageable);
    @Query("SELECT c.fabricante, COUNT(c) AS count FROM Carro c GROUP BY c.fabricante ORDER BY count DESC")
    Page<Object[]> findTop10Fabricantes(Pageable pageable);


}
