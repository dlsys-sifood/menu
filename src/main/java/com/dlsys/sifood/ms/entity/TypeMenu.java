package com.dlsys.sifood.ms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "type_menu")
public class TypeMenu implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    @NotEmpty(message = "no puede ser vacio")
    private String name;

    @Column
    private Integer flag;

    @JsonIgnore
    @OneToMany(mappedBy = "typeMenu", fetch = FetchType.LAZY)
    private List<Menu> menu;

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> product;

    @PrePersist
    public void beforeSave(){
        this.flag=1;
    }
}
