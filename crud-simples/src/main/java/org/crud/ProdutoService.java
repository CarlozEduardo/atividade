package org.crud;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProdutoService {
    public List<Produto> getProduto() {
        return Produto.listAll();
    }

    @Transactional
    public boolean postProduto(Produto produto) {
        if (Produto.findById(produto.getId()) == null) {
            Produto.persist(produto);
        }
    }
     @Transactional
    public boolean putProduto(Produto produtoAtualizado) {
         Produto produtoAntigo = Produto.findById(produtoAtualizado.getId());
         if (produtoAntigo != null) {
             produtoAntigo.setNome(produtoAtualizado.getNome());
             produtoAntigo.setPreco(produtoAtualizado.getPreco());
         }
    }

    @Transactional
    public boolean delProduto(Integer id) {
        Produto.deleteById(id);
    }
}
