package com.engine.promotion.service;

import com.engine.promotion.model.Sku;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements PromotionService {

    private List<Promotion> promotions;

    @Value("${skuIdAUnitPrice}")
    private Double skuIdAUnitPrice;
    @Value("${skuIdBUnitPrice}")
    private Double skuIdBUnitPrice;
    @Value("${skuIdCUnitPrice}")
    private Double skuIdCUnitPrice;
    @Value("${skuIdDUnitPrice}")
    private Double skuIdDUnitPrice;

    @PostConstruct
    public void initializeActivePromotions() {
        promotions =  new ArrayList<>();
        promotions.add(new FixedPriceNItemsOneSkuPromotion("A", 3, 130));
        promotions.add(new FixedPriceNItemsOneSkuPromotion("B", 2, 45));
        promotions.add(new SkuCombinedFixedPricePromotion("C", "D", 30));
    }
    @Override
    public double calculateTotalPrice(List<String> skuIds) {
            // Here we are creating SKU list based on skuIds and the given unit price
            List<Sku> skus = skuIds.stream()
                    .map(skuId -> {
                        switch (skuId) {
                            case "A":
                                return new Sku("A", skuIdAUnitPrice);
                            case "B":
                                return new Sku("B", skuIdBUnitPrice);
                            case "C":
                                return new Sku("C", skuIdCUnitPrice);
                            case "D":
                                return new Sku("D", skuIdDUnitPrice);
                            default:
                                throw new IllegalArgumentException("Invalid SKU ID: " + skuId);
                        }
                    })
                    .collect(Collectors.toList());

            // Initially the total cost price is calculated for the above collected skus
            double totalPrice = skus.stream()
                    .mapToDouble(Sku::getUnitPrice)
                    .sum();
            for (Promotion promotion : promotions) {
                //Here we check if the SKU is applicable for promotion and if yes -then apply the promotion and calculate the total price
                if (promotion.isApplicable(skus)) {
                    totalPrice -= promotion.applyPromotion(skus);
                }
            }
            return totalPrice;
        }
}
