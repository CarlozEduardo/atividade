package com.rest;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Produces;

@Path("/produtos")
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
    @Path("/{nome}")
    public void atualizarProduto(@PathParam("nome") String nome, Produto produtoAtualizado) {
        for (Produto produto : produtos) {
            if (produto.getNome().equals(nome)) {
                produto.setPreco(produtoAtualizado.getPreco());
                break;
            }
        }
    }

    @DELETE
    @Path("/{nome}")
    public void removerProduto(@PathParam("nome") String nome) {
        produtos.removeIf(produto -> produto.getNome().equals(nome));
    }
}
