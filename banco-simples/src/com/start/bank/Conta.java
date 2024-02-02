package com.start.bank;

public class Conta {

    private String nome;

    private String senha;
    private Integer agencia;
    private double saldo;

    public Conta(String nome, String senha, Integer agencia) {
        this.nome = nome;
        this.senha = senha;
        this.agencia = agencia;
        this.saldo = 0.0;
    }

    public String getNome(){
        return nome;
    }

    public String getSenha(){
        return senha;
    }

    public Integer getAgencia(){
        return agencia;
    }
}
