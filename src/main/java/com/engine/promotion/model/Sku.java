package com.engine.promotion.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sku {

    private String skuId;
    private double unitPrice;
}
