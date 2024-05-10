package br.com.masterclass.superpecas.service;

import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarroService {

    private final CarroRepository carroRepository;

    @Autowired
    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public List<Carro> findAll() {
        return carroRepository.findAll();
    }

    public Carro findById(int carroID) {
        return carroRepository.findById(carroID).orElse(null);
    }

    public Carro save(Carro carro) {
        return carroRepository.save(carro);
    }

    public void deleteById(int carroID){
        carroRepository.deleteById(carroID);
    }

    public List<String> listaTodosFabricantes() {
        return carroRepository.findAllFabricantes();
    }

    public List<Carro> listaTodosPaginado(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Carro> carrosPaginados = carroRepository.findAll(pageable);
        return carrosPaginados.getContent();
    }

    public List<Carro> listaTodosPaginado(String termo, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Carro> carrosPaginados;

        if (termo != null && !termo.isEmpty()) {
            carrosPaginados = carroRepository.findAllByNomeModeloContainingIgnoreCase(termo, pageable);
        } else {
            carrosPaginados = carroRepository.findAll(pageable);
        }

        return carrosPaginados.getContent();
    }

    public Page<Object[]> listaTop10Fabricantes(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return carroRepository.findTop10Fabricantes(pageable);
    }
}
