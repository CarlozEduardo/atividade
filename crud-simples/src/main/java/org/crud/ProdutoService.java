package org.crud;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class ProdutoService {
    public Response getProduto() {
            return Response.status(Response.Status.OK).entity(Produto.listAll()).build();
    }

    @Transactional
    public Response postProduto(Produto produto) {
        if (Produto.findById(produto.getId()) == null) {
            Produto.persist(produto);
            return Response.status(Response.Status.CREATED).entity("Produto cadastrado com sucesso!").build();
        }
        return Response.status(Response.Status.CONFLICT).entity("Produto já existente!").build();
    }
     @Transactional
    public Response putProduto(Produto produtoAtualizado) {
         Produto produtoAntigo = Produto.findById(produtoAtualizado.getId());
         if (produtoAntigo != null) {
             produtoAntigo.setNome(produtoAtualizado.getNome());
             produtoAntigo.setPreco(produtoAtualizado.getPreco());

             return Response.status(Response.Status.OK).entity("Produto atualizado com sucesso!").build();
         }
         return Response.status(Response.Status.NOT_FOUND).entity("Produto não encontrado!").build();
     }

    @Transactional
    public Response delProduto(Integer id) {
        if (Produto.deleteById(id)) {
            return Response.status(Response.Status.OK).entity("Produto removido com sucesso!").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Produto não encontrado!").build();
    }
}
