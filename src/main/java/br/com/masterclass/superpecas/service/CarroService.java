package br.com.masterclass.superpecas.service;

import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
