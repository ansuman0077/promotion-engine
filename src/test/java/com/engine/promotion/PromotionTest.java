package com.engine.promotion;

import com.engine.promotion.model.Sku;
import com.engine.promotion.service.FixedPriceNItemsOneSkuPromotion;
import com.engine.promotion.service.Promotion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PromotionTest {

    @Test
    public void testBuyNItemsForFixedPricePromotionIsApplicable() {
        Promotion promotion = new FixedPriceNItemsOneSkuPromotion("A", 3, 130.0);
        List<Sku> skus = new ArrayList<>();
        skus.add(new Sku("A", 50));
        skus.add(new Sku("A", 50));
        skus.add(new Sku("A", 50));
        skus.add(new Sku("B", 30));
        assertTrue(promotion.isApplicable(skus));
    }
}
