package com.dlsys.sifood.ms.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "menu")
public class Menu implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @GeneratedValue
    private UUID id;

    @Column
    private String name;

    @Column
    private Integer flag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_type_menu")
    private TypeMenu typeMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    private Product product;

    @PrePersist
    public void BeforeSave(){
        this.flag = 1;
    }
}
