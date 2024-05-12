package br.com.masterclass.superpecas.DTO;

public class CarroDTO {
    private int carroID;
    private String nomeModelo;
    private String fabricante;
    private String codigoUnico;

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public int getCarroID() {
        return carroID;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setCarroID(int carroID) {
        this.carroID = carroID;
    }
}
