package org.crud;

import jakarta.transaction.Transactional;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.management.Query;
import java.util.List;

@Path("/produto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    @GET
    @Operation(summary = "Retorna lista de produtos cadastrados")
    @APIResponse(responseCode = "201",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = Produto.class,
                            type = SchemaType.ARRAY)))
    @Transactional
    public Response listarProdutos() {
        return produtoService.getProduto();
    }

    @GET
    @Path("/{preco}")
    @Operation(summary = "Retorna lista de produtos até o preço obtido pelo parametro")
    @APIResponse(responseCode = "201",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = Produto.class,
                            type = SchemaType.ARRAY)))
    @APIResponse(responseCode = "404", description = "Nenhum produto com esse preço")
    @Transactional
    public Response listarProdutosPeloPreco(@PathParam("preco") Double precoMax) {
        return produtoService.listarProdutosPeloPreco(precoMax);
    }


    @POST
    @Operation(summary = "Cadastro de produto", description = "Cadastro de produto.")
    @APIResponse(responseCode = "201", description = "Produto cadastrado com sucesso!")
    @APIResponse(responseCode = "409", description = "Produto já existente!")
    @Transactional
    public Response criarProduto(Produto produto) {
        return produtoService.postProduto(produto);
    }

    @PUT
    @Operation(summary = "Atualiza informações do produto", description = "Atualiza informações do produto")
    @APIResponse(responseCode = "200", description = "Produto atualizado com sucesso!")
    @APIResponse(responseCode = "404", description = "Produto não encontrado!")
    @Transactional
    public Response atualizarProduto(Produto produto) {
        return produtoService.putProduto(produto);
    }

    @DELETE
    @Operation(summary = "Remove um produto", description = "Remove um produto")
    @APIResponse(responseCode = "201", description = "Produto removido com sucesso!")
    @APIResponse(responseCode = "404", description = "Produto não encontrado!")
    public Response deletarProduto(Produto produto) {
        Integer id = produto.getId();
        return produtoService.delProduto(id);
    }
}
