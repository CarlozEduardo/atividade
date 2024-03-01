package org.crud;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Produto extends PanacheEntityBase {
    @Id
    private Integer id;

    private String nome;

    private Double preco;
}
