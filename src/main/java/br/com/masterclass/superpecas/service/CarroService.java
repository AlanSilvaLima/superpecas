package br.com.masterclass.superpecas.service;

import br.com.masterclass.superpecas.DTO.CarroDTO;
import br.com.masterclass.superpecas.model.Carro;
import br.com.masterclass.superpecas.repository.CarroRepository;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper  modelMapper;

    @Autowired
    public CarroService(CarroRepository carroRepository, ModelMapper modelMapper) {
        this.carroRepository = carroRepository;
        this.modelMapper = modelMapper;
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

    public Page<CarroDTO> listaTodosPaginadoDTO(Pageable pageable) {
        Page<Carro> carros = carroRepository.findAll(pageable);
        return carros.map(carro -> modelMapper.map(carro, CarroDTO.class));
    }

    public Page<CarroDTO> listaTodosPaginadoDTO(String termo, Pageable pageable) {
        Page<Carro> carrosPage;
        if (termo != null && !termo.isEmpty()) {
            carrosPage = carroRepository.findAllByNomeModeloContainingIgnoreCaseOrFabricanteContainingIgnoreCaseOrCodigoUnicoContainingIgnoreCase(
                    termo, termo, termo, pageable);
        } else {
            carrosPage = carroRepository.findAll(pageable);
        }
        return carrosPage.map(carro -> modelMapper.map(carro, CarroDTO.class));
    }

    public Page<Object[]> listaTop10Fabricantes(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return carroRepository.findTop10Fabricantes(pageable);
    }
}
