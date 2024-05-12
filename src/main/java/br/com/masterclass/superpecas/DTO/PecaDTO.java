package br.com.masterclass.superpecas.DTO;

import io.swagger.annotations.ApiModelProperty;

public class PecaDTO {

    @ApiModelProperty(notes = "ID da peça")
    private int pecaID;
    @ApiModelProperty(notes = "nome da peça")
    private String nome;
    @ApiModelProperty(notes = "numero de serie da peça")
    private String numeroSerie;
    @ApiModelProperty(notes = "fabricante da peça")
    private String fabricante;
    @ApiModelProperty(notes = "modelo do carro da peça")
    private String modeloCarro;
    @ApiModelProperty(notes = "Carro ID da peça")
    private int carroID;

    public int getCarroID() {
        return carroID;
    }

    public void setCarroID(int carroID) {
        this.carroID = carroID;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public int getPecaID() {
        return pecaID;
    }

    public void setPecaID(int pecaID) {
        this.pecaID = pecaID;
    }
}


