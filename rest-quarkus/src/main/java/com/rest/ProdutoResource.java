package com.rest;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Produces;

@Path("/produto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    private static List<Produto> produtos = new ArrayList<>();

    @GET
    public List<Produto> listarProdutos() {
        return produtos;
    }

    @GET
    @Path("/produto")
    public Produto pesquisarPeloId(@QueryParam("nome") String produtoNome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equals(produtoNome)) {
                return produto;
            }
        }
        return null;
    }


    @POST
    public void adicionarProduto(Produto produto) {
    ;    produtos.add(produto);
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

    @PUT
    @Path("/{nome}")
    public void atualizarProdutoQuery(@PathParam("nome") String produtoNome, Produto precoAtualizado) {
        for (Produto produto : produtos) {
            if (produto.getNome().equals(produtoNome)) {
                produto.setPreco(precoAtualizado.getPreco());
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
