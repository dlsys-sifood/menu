package com.dlsys.sifood.ms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    @GeneratedValue
    private UUID id;

    @Column
    @NotEmpty(message = "no puede ser vacio")
    private String name;

    @Column
    @NotNull(message = "no puede ser vacio")
    private Double price;

    @Column
    @NotEmpty(message = "no puede ser vacio")
    private String description;

    @Column
    @NotEmpty(message = "no puede ser vacio")
    private String image;

    @Column(name = "time_stimation")
    @NotEmpty(message = "no puede ser vacio")
    private String timeStimation;

    @Column
    @NotNull(message = "no puede ser vacio")
    private Float iva;

    @Column
    private Integer flag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product_category")
    private ProductCategory category;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Menu> menu;

    @PrePersist
    public void beforeSave(){
        this.flag = 1;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .toString();
    }
}
