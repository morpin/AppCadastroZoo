package com.morpin.appcadastrozoo.model;

/**
 * Created by android on 10/09/2018.
 */

public class Animal {

    private int id;
    private String nome;
    private double idade;
    private Especie especie;

    public Animal() {

    }

    public Animal(int id, String nome, double idade, Especie especie) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.especie = especie;
    }

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

    public double getIdade() {
        return idade;
    }

    public void setIdade(double idade) {
        this.idade = idade;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }


    @Override
    public String toString() {
        return nome;
    }

}
