package com.engine.promotion.service;

import com.engine.promotion.model.Sku;
import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class SkuCombinedFixedPricePromotion implements Promotion {

    private final String skuIdOne;
    private final String skuIdTwo;
    private final double fixedPrice;

    @Override
    public double applyPromotion(List<Sku> skus) {
        // calculate the total price of the SKUs with the specified SKU Ids in the cart
        double totalPrice = skus.stream()
                .filter(sku -> sku.getSkuId().equals(skuIdOne) || sku.getSkuId().equals(skuIdTwo))
                .mapToDouble(Sku::getUnitPrice)
                .sum();

        // calculate the discount amount if the SKU is eligible for the promotion
        double discount = 0;
        if (isApplicable(skus)) {
            discount = totalPrice - fixedPrice;
        }
        return discount;
    }

    @Override
    public boolean isApplicable(List<Sku> skus) {
        // check if both the SKUs are available in the cart
        boolean hasSku1 = skus.stream()
                .anyMatch(sku -> sku.getSkuId().equals(skuIdOne));
        boolean hasSku2 = skus.stream()
                .anyMatch(sku -> sku.getSkuId().equals(skuIdTwo));
        return hasSku1 && hasSku2;
    }
}
