package com.engine.promotion.service;

import com.engine.promotion.model.Sku;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private List<Promotion> promotions;
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
                                return new Sku("A", 50);
                            case "B":
                                return new Sku("B", 30);
                            case "C":
                                return new Sku("C", 20);
                            case "D":
                                return new Sku("D", 15);
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
