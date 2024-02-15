package org.crud;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProdutoService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public void postProduto(Produto produto) {
        entityManager.persist(produto);
    }

    public List<Produto> getProduto() {
         return entityManager.createQuery("SELECT p FROM Produto p", Produto.class)
                .getResultList();
    }

    /* @Transactional
    public void atualizarExemplo(Produto exemplo) {
        entityManager.merge(exemplo);
    }

    @Transactional
    public void deletarExemplo(Long id) {
        Produto exemplo = entityManager.find(Produto.class, id);
        if (exemplo != null) {
            entityManager.remove(exemplo);
        }
    } */
}
