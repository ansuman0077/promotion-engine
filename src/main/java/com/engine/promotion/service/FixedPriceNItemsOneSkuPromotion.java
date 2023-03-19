package com.engine.promotion.service;

import com.engine.promotion.model.Sku;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FixedPriceNItemsOneSkuPromotion implements Promotion {

    private final String skuId;
    private final int quantity;
    private final double fixedPrice;

    @Override
    public double applyPromotion(List<Sku> skus) {
        // count the number of SKUs with the specified skuId that is added in the cart
        long count = skus.stream()
                .filter(sku -> sku.getSkuId().equals(skuId))
                .count();

        // calculate the discount amount based on promotion
        double discount = 0;
        if (count >= quantity) {
            double totalPrice = quantity * skus.stream()
                    .filter(sku -> sku.getSkuId().equals(skuId))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new)
                    .getUnitPrice();
            discount = totalPrice - fixedPrice;
        }
        return discount;
    }

    @Override
    public boolean isApplicable(List<Sku> skus) {
        // Here we check if the total number of SKUs are present in the cart which are required to be eligible for a promotion
        long count = skus.stream()
                .filter(sku -> sku.getSkuId().equals(skuId))
                .count();
        return count >= quantity;
    }
}
