package br.com.masterclass.superpecas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Pecas")
@NoArgsConstructor
@AllArgsConstructor
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PecaID")
    private int pecaID;

    @NotBlank
    @Size(max = 255)
    @Column(name = "Nome")
    private String nome;

    @NotBlank
    @Size(max = 255)
    @Column(name = "NumeroSerie", unique = true)
    private String numeroSerie;

    @NotBlank
    @Size(max = 255)
    @Column(name = "Fabricante")
    private String fabricante;

    @NotBlank
    @Size(max = 255)
    @Column(name = "ModeloCarro")
    private String modeloCarro;

    @NotNull
    @Column(name = "CarroID")
    private int carroID;

    public int getCarroID() {
        return carroID;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public int getPecaID() {
        return pecaID;
    }

    public void setPecaID(int pecaID) {
        this.pecaID = pecaID;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setCarroID(int carroID) {
        this.carroID = carroID;
    }
}
