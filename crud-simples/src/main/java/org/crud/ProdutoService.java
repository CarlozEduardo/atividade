package org.crud;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProdutoService {

    Produto entidade = new Produto();

    @Transactional
    public void postProduto(Produto produto) {
        Produto.persist(produto);
    }

    public List<Produto> getProduto() {
        return entidade.listAll();
    }

     @Transactional
    public void putProduto(Produto produtoAtualizado) {
         Produto produtoAntigo = Produto.findById(produtoAtualizado.getId());
         if (produtoAntigo != null) {
             produtoAntigo.setNome(produtoAtualizado.getNome());
             produtoAntigo.setPreco(produtoAtualizado.getPreco());
         }
    }

    @Transactional
    public void delProduto(Integer id) {
        entidade.deleteById(id);
    }
}
