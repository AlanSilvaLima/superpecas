package br.com.masterclass.superpecas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/carro")

public class CarroController {


    @RequestMapping(value = "/", method = RequestMethod.GET)

    public String buscaCarro() {

        return "teste";

    }


}
