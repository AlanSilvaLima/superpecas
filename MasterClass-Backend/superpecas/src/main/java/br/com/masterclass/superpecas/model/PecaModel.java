package br.com.masterclass.superpecas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Pecas")
public class PecaModel {

    @Id
    @Column(name = "PecaID", nullable = false)
    int id;

    @Column(name = "Nome", nullable = false)
    String nome;

    @Column(name = "Descricao", nullable = false)
    String descricao;

    @Column(name = "NumeroSerie", nullable = false)
    String numeroSerie;

    @Column(name = "Fabricante", nullable = false)
    String fabricante;

    @Column(name = "ModeloCarro", nullable = false)
    String modeloCarro;

    @OneToOne(optional = false)
    @JoinColumn(name = "CarroID", nullable = false)
    CarroModel carro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
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

    public CarroModel getCarro() {
        return carro;
    }

    public void setCarro(CarroModel carro) {
        this.carro = carro;
    }
}
