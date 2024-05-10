package br.com.masterclass.superpecas.service;

import br.com.masterclass.superpecas.model.Peca;
import br.com.masterclass.superpecas.repository.CarroRepository;
import br.com.masterclass.superpecas.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PecaService {

    private final PecaRepository pecaRepository;


    @Autowired
    public PecaService(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
    }

    public List<Peca> findAll() {
        return pecaRepository.findAll();
    }

    public Optional<Peca> findById(int pecaID) {
        return pecaRepository.findById(pecaID);
    }

    public Peca save(Peca peca) {
        return pecaRepository.save(peca);
    }

    public void deleteById(int pecaID) {
        pecaRepository.deleteById(pecaID);
    }

    public List<Peca> listaTodosPaginado(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Peca> pecasPaginadas = pecaRepository.findAll(pageable);
        return pecasPaginadas.getContent();
    }

    public List<Peca> listaTodosPaginado(String termo, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Peca> pecasPaginadas;

        if (termo != null && !termo.isEmpty()) {
            pecasPaginadas = pecaRepository.findByNomeContainingIgnoreCase(termo, pageable);
        } else {
            pecasPaginadas = pecaRepository.findAll(pageable);
        }

        return pecasPaginadas.getContent();
    }

    public List<Object[]> listaTop10CarroComMaisPecas() {
        return pecaRepository.findTop10CarroComMaisPecas();
    }

}
