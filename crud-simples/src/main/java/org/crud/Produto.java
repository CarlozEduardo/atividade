package org.crud;

import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Produto extends PanacheEntityBase {
    @Id
    private Integer id;

    private String nome;

    private Double preco;

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public Double getPreco() {
        return preco;
    }

    public static List<Produto> getProdutoPeloFiltro(Double precoMax) {
        return list ("from Produto p "
                                        +
                                        "where p.preco >= "
                                        + precoMax.toString());
    }
}
