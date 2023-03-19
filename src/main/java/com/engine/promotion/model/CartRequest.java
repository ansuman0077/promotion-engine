package com.engine.promotion.model;

import lombok.Data;

import java.util.List;

@Data
public class CartRequest {

    private List<String> skuIds;

}
