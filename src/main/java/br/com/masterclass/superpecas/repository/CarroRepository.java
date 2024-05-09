package br.com.masterclass.superpecas.repository;

import br.com.masterclass.superpecas.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CarroRepository extends JpaRepository<Carro, Integer> {
}
