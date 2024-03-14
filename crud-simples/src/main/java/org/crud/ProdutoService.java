package org.crud;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProdutoService {

    Resposta res = new Resposta();

    List<Produto> listaProduto = new ArrayList<>();

    public Response getProduto() {
            return Response.status(Response.Status.OK).entity(Produto.listAll()).build();
    }

    public Response listarProdutosPeloPreco(Double precoMax) {
//        List<Produto> listaFiltrada = listaProduto.stream()
//                .filter(produto -> produto.getPreco() <= precoMax).toList();

        if (!Produto.getProdutoAcimaDeDez(precoMax).isEmpty()) {
            return Response.status(Response.Status.OK).entity(Produto.getProdutoAcimaDeDez(precoMax)).build();
        } else {
            res.setDescricao("Nenhum produto com esse preço");
            return Response.status(Response.Status.NOT_FOUND).entity(res).build();
        }
    }

    @Transactional
    public Response postProduto(Produto produto) {
        res.setDescricao("Produto já existente!");
        if (Produto.findById(produto.getId()) == null) {
            Produto.persist(produto);
            res.setDescricao("Produto cadastrado com sucesso");
            return Response.status(Response.Status.CREATED).entity(res.getDescricao()).build();
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
