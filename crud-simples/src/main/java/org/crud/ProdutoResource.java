package org.crud;

import jakarta.transaction.Transactional;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

@Path("/produto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    @GET
    @Operation(summary = "Retorna lista de produtos cadastrados")
    @APIResponse(responseCode = "404", description = "Nenhum conteúdo encontrado!")
    @Transactional
    public List<Produto> listarProdutos() {
        return produtoService.getProduto();
    }


    @POST
    @Operation(summary = "Cadastro de produto", description = "Cadastro de produto.")
    @APIResponse(responseCode = "201", description = "Produto cadastrado com sucesso!")
    @APIResponse(responseCode = "404", description = "Produto já existente!")
    @Transactional
    public Response criarProduto(Produto produto) {
        boolean postou = produtoService.postProduto(produto);
        return postou ? Response.ok("Produto cadastrado com sucesso!").build()
                : Response.status(Response.Status.NOT_FOUND).entity("Produto já existente!").build();
    }

    @PUT
    @Operation(summary = "Atualiza informações do produto", description = "Atualiza informações do produto")
    @APIResponse(responseCode = "200", description = "Produto atualizado com sucesso!")
    @APIResponse(responseCode = "404", description = "Produto não encontrado!")
    @Transactional
    public Response atualizarProduto(Produto produto) {
        boolean atualizou = produtoService.putProduto(produto);
        return atualizou ? Response.ok("Produto atualizado com sucesso!").build()
                : Response.status(Response.Status.NOT_FOUND).entity("Produto não encontrado!").build();
    }

    @DELETE
    @Operation(summary = "Remove um produto", description = "Remove um produto")
    @APIResponse(responseCode = "200", description = "Produto removido com sucesso!")
    @APIResponse(responseCode = "404", description = "Produto não encontrado!")
    public Response deletarProduto(Produto produto) {
        Integer id = produto.getId();
        boolean removeu = produtoService.delProduto(id);
        return removeu ? Response.ok("Produto removido com sucesso!").build()
                : Response.status(Response.Status.NOT_FOUND).entity("Produto não encontrado!").build();
    }
}
