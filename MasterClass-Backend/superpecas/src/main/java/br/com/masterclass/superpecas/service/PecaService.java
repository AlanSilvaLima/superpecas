package br.com.masterclass.superpecas.service;

import br.com.masterclass.superpecas.model.CarroModel;
import br.com.masterclass.superpecas.model.DTO.TopCarroPecasDTO;
import br.com.masterclass.superpecas.model.DTO.TopFabricantesDTO;
import br.com.masterclass.superpecas.model.PecaModel;
import br.com.masterclass.superpecas.repository.CarroRepository;
import br.com.masterclass.superpecas.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PecaService {

    @Autowired
    PecaRepository pecaRepository;

    public PecaModel buscaPeca(int id){
        return pecaRepository.findById(id).orElse(null);
    }

    public List<PecaModel> listaPecas(){
        return  pecaRepository.findAll();
    }

    public Page<PecaModel> listaPecasPaginado(Pageable pageable){
        return pecaRepository.findAll(pageable);
    }

    public Page<PecaModel> listaPecasPorNomeEOuNumeroSerie(String termo, Pageable pageable){
        return pecaRepository.findByNomeOrNumeroSerieOrFabricanteOrModeloCarro(termo, pageable);
    }

    public PecaModel gravaPeca(PecaModel peca){
        PecaModel pecaModel = pecaRepository.findByNomeAndNumeroSerie(peca.getNome(), peca.getNumeroSerie());

        if (pecaModel == null){
            return pecaRepository.save(peca);
        }

        return null;
    }

    public PecaModel editaPeca(PecaModel peca){
        PecaModel pecaModel = pecaRepository.findById(peca.getId()).orElse(null);

        if (pecaModel == null){
            return null;
        }

        PecaModel existeMesmoNome = pecaRepository.findByNomeAndNumeroSerie(peca.getNome(), peca.getNumeroSerie());

        if (existeMesmoNome != null && existeMesmoNome.getId()!= peca.getId()){
            return null;
        }

        return pecaRepository.save(peca);
    }

    public void excluiPeca(int id){
        PecaModel pecaModel = pecaRepository.findById(id).orElse(null);
        if (pecaModel != null)
            pecaRepository.delete(pecaModel);
    }

    public List<PecaModel> listaPecasPorCarroId(int carroId){
        return pecaRepository.findByCarroId(carroId);
    }

    public List<TopCarroPecasDTO> listaTop10CarrosComMaisPecas(){
        return pecaRepository.findTop10CarrosComMaisPecas();
    }

}
