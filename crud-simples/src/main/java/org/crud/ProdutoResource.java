package org.crud;

import jakarta.transaction.Transactional;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import static io.quarkus.hibernate.orm.panache.PanacheEntity_.id;

@Path("/produto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    @GET
    @Transactional
    public List<Produto> listarProdutos() {
        return produtoService.getProduto();
    }


    @POST
    @Transactional
    public void criarProduto(Produto produto) {
        produtoService.postProduto(produto);
    }

    @PUT
    @Transactional
    public void atualizarProduto(Produto produto) {
        produtoService.putProduto(produto);
    }

    @DELETE
    public void deletarProduto(Produto produto) {
        Integer id = produto.id;
        produtoService.delProduto(id);
    }
}
