package com.start.bank;

import java.util.Scanner;

public class Start {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Gerenciamento gerenciador = new Gerenciamento();

        System.out.println("Bem vindo escolha uma das opções:\n1- Sign in\n2- Login\n3- Quit");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1: // Cadastro
                System.out.println("Para realizar o cadastro digite algumas informações solicitadas a seguir:\n");

                System.out.println("Digite seu nome:");
                String nome = sc.next();

                System.out.println("Crie sua senha:");
                String senha = sc.next();

                int agencia = (int) (Math.random() * 10 + 1); // Criando agência aleatória

                boolean cadastrou = gerenciador.cadastro(nome, senha, agencia);

                if (cadastrou) {
                    System.out.println("Cadastro efetuado com sucesso!\n Bem vindo " + nome +
                            " ao Banco Stefanini sua agência é " + agencia);
                } else {
                    System.out.println("Já existe uma conta com esse nome");
                    break;
                }

            case 2: // Login
                System.out.println("Realize o login ");

                System.out.println("Digite seu nome:");
                String nomel = sc.next();

                System.out.println("Digite sua senha:");
                String senhal = sc.next();

                System.out.println("Digite o número da sua agência:");
                Integer agencial = sc.nextInt();

                boolean logou = gerenciador.login(nomel, senhal, agencial);

                if (logou) {
                    System.out.println("Login efetuado");
                } else {
                    System.out.println("Erro no login");
                }
                break;
            case 3: // Encerrar
                System.out.println("Encerrando...");
                break;
            default:
                System.out.println("Error 989");
        }
    }
}
