package com.dlsys.sifood.ms.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearch extends GenericSearch{
    private String price;
    private String timeStimation;
}
