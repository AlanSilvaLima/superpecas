package br.com.masterclass.superpecas.service;

import br.com.masterclass.superpecas.DTO.PecaDTO;
import br.com.masterclass.superpecas.model.Peca;
import br.com.masterclass.superpecas.repository.CarroRepository;
import br.com.masterclass.superpecas.repository.PecaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PecaService {

    private final PecaRepository pecaRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public PecaService(PecaRepository pecaRepository, ModelMapper modelMapper) {
        this.pecaRepository = pecaRepository;
        this.modelMapper = modelMapper;
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

    public Page<PecaDTO> listaTodosPaginadoDTO(Pageable pageable) {
        Page<Peca> pecasPage = pecaRepository.findAll(pageable);
        return pecasPage.map(peca -> modelMapper.map(peca, PecaDTO.class));
    }

    public Page<PecaDTO> listaTodosPaginadoDTO(String termo, Pageable pageable) {
        Page<Peca> pecasPage = pecaRepository.findByNomeContainingIgnoreCaseOrNumeroSerieContainingIgnoreCaseOrFabricanteContainingIgnoreCaseOrModeloCarroContainingIgnoreCase(
                termo,
                termo,
                termo,
                termo,
                pageable
        );
        return pecasPage.map(peca -> modelMapper.map(peca, PecaDTO.class));
    }

    public List<Object[]> listaTop10CarroComMaisPecas() {
        return pecaRepository.findTop10CarroComMaisPecas();
    }

}
