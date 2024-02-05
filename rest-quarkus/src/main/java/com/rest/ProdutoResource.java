package com.rest;

import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Produces;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    private static List<Produto> produtos = new ArrayList<>();

    @GET
    public List<Produto> listarProdutos() {
        return produtos;
    }

    @POST
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    @PUT
    public void atualizarProduto(Produto produtoAtualizado) {
        for (Produto produto : produtos) {
            if (produto.getId().equals(produtoAtualizado.getId())) {
                produto.setNome(produtoAtualizado.getNome());
                produto.setPreco(produtoAtualizado.getPreco());
                break;
            }
        }
    }

    @DELETE
    public void removerProduto(Produto id) {
        produtos.removeIf(
                produto -> produto.getId().equals(id.getId())
        );
    }
}
