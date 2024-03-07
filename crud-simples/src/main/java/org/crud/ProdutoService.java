package org.crud;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ProdutoService {

    Resposta res = new Resposta();

    public Response getProduto() {
            return Response.status(Response.Status.OK).entity(Produto.listAll()).build();
    }

    @Transactional
    public Response postProduto(Produto produto) {
        res.setDescricao("Produto já existente!");
        if (Produto.findById(produto.getId()) == null) {
            Produto.persist(produto);
            res.setDescricao("Produto cadastrado com sucesso");
            return Response.status(Response.Status.CREATED).entity(res).build();
        }
        return Response.status(Response.Status.CONFLICT).entity(produto).build();
    }
     @Transactional
    public Response putProduto(Produto produtoAtualizado) {
         res.setDescricao("Produto não encontrado!");
         Produto produtoAntigo = Produto.findById(produtoAtualizado.getId());
         if (produtoAntigo != null) {
             produtoAntigo.setNome(produtoAtualizado.getNome());
             produtoAntigo.setPreco(produtoAtualizado.getPreco());

             res.setDescricao("Produto atualizado com sucesso!");
             return Response.status(Response.Status.OK).entity(res.getDescricao()).build();
         }
         return Response.status(Response.Status.NOT_FOUND).entity(res.getDescricao()).build();
     }

    @Transactional
    public Response delProduto(Integer id) {
        res.setDescricao("Produto não encontrado!");
        if (Produto.deleteById(id)) {
            res.setDescricao("Produto removido com sucesso!");
            return Response.status(Response.Status.OK).entity(res.getDescricao()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(res.getDescricao()).build();
    }
}
