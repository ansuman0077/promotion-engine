package com.engine.promotion;

import com.engine.promotion.model.Sku;
import com.engine.promotion.service.FixedPriceNItemsOneSkuPromotion;
import com.engine.promotion.service.Promotion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PromotionTest {

    @Test
    public void promotionIsApplicableScenarioOneForFixedPriceNItems() {
        Promotion promotion = new FixedPriceNItemsOneSkuPromotion("A", 3, 130.0);
        List<Sku> skus = new ArrayList<>();
        skus.add(new Sku("A", 50));
        skus.add(new Sku("A", 50));
        skus.add(new Sku("A", 50));
        skus.add(new Sku("B", 30));
        assertTrue(promotion.isApplicable(skus));
    }

    @Test
    public void promotionIsApplicableScenarioTwoForFixedPriceNItems() {
        Promotion promotion = new FixedPriceNItemsOneSkuPromotion("B", 2, 45.0);
        List<Sku> skus = new ArrayList<>();
        skus.add(new Sku("B", 30));
        skus.add(new Sku("B", 30));
        assertTrue(promotion.isApplicable(skus));
    }

    @Test
    public void promotionIsNotApplicableScenarioForFixedPriceNItems() {
        Promotion promotion = new FixedPriceNItemsOneSkuPromotion("A", 3, 130.0);
        List<Sku> skus = new ArrayList<>();
        skus.add(new Sku("A", 50));
        skus.add(new Sku("A", 50));
        skus.add(new Sku("B", 30));
        assertFalse(promotion.isApplicable(skus));
    }

    @Test
    public void applyPromotionScenarioOneForFixedPriceNItems() {
        Promotion promotion = new FixedPriceNItemsOneSkuPromotion("A", 3, 130.0);
        List<Sku> skus = new ArrayList<>();
        skus.add(new Sku("A", 50));
        skus.add(new Sku("A", 50));
        skus.add(new Sku("A", 50));
        skus.add(new Sku("B", 30));
        double discount = promotion.applyPromotion(skus);
        assertNotNull(discount);
        assertEquals(20.0,discount);
    }

    @Test
    public void applyPromotionScenarioTwoForFixedPriceNItems() {
        Promotion promotion = new FixedPriceNItemsOneSkuPromotion("B", 2, 45.0);
        List<Sku> skus = new ArrayList<>();
        skus.add(new Sku("B", 30));
        skus.add(new Sku("B", 30));
        double discount = promotion.applyPromotion(skus);
        assertNotNull(discount);
        assertEquals(15.0,discount);
    }
}
