package com.engine.promotion.service;

import com.engine.promotion.model.Sku;

import java.util.List;

public interface Promotion {

    double applyPromotion(List<Sku> skus);

    boolean isApplicable(List<Sku> skus);
}
