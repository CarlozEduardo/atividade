package com.rest;

public class Produto {

    private Integer id;
    private String nome;
    private double preco;

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

     public void setId(Integer id) {
         this.id = id;
     }

    // construtores, getters e setters
}
