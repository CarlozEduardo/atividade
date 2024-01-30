package com.start.bank;

import java.util.ArrayList;
import java.util.List;

public class Gerenciamento {
    private List<Conta> usuarios;
    public Gerenciamento() {
        this.usuarios = new ArrayList<>();

        // adicionando contas a array
        usuarios.add(new Conta("carlo", "123", 10));
        usuarios.add(new Conta("hudson", "123", 9));
    }

    public boolean cadastro(String nomec, String senhac, Integer agenciac) {

        for (Conta conta : usuarios) {
            if (conta.getNome().equals(nomec)) {
                // Já existe uma conta com esse nome
                return false;
            }
        }

        Conta novaConta = new Conta(nomec, senhac, agenciac);

        usuarios.add(novaConta);
        return true;
    }

    public boolean login(String nomel, String senhal, Integer agencial) {
        for (Conta conta : usuarios) {
            if (conta.getNome().equals(nomel) && conta.getSenha().equals(senhal) && conta.getAgencia().equals(agencial)) {

                System.out.println("Atualmente temos " + usuarios.stream().count() + " usuarios");

                return true;
                // Login efetuado
            }
        }
        return false;
    }
}