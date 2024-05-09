package br.com.masterclass.superpecas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Carros")
@NoArgsConstructor
@AllArgsConstructor

public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarroID")
    private int carroID;

    @Column(name = "NomeModelo")
    private String nomeModelo;

    @Column(name = "Fabricante")
    private String fabricante;

    @Column(name = "CodigoUnico", unique = true)
    private String codigoUnico;

    public int getCarroID() {
        return carroID;
    }

    public void setCarroID(int carroID) {
        this.carroID = carroID;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }
}
