package com.engine.promotion;

import com.engine.promotion.service.FixedPriceNItemsOneSkuPromotion;
import com.engine.promotion.service.PromotionService;
import com.engine.promotion.service.SkuCombinedFixedPricePromotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PromotionServiceTest {

    @Autowired
    private PromotionService promotionService;

    @BeforeEach
    public void initializeActivePromotions(){
        List promotions =  new ArrayList<>();
        promotions.add(new FixedPriceNItemsOneSkuPromotion("A", 3, 130));
        promotions.add(new FixedPriceNItemsOneSkuPromotion("B", 2, 45));
        promotions.add(new SkuCombinedFixedPricePromotion("C", "D", 30));
    }
    @Test
    public void calculateTotalPriceWithNoPromotions() {
        List<String> skuIds = new ArrayList<>();
        skuIds.add("A");
        skuIds.add("B");
        skuIds.add("C");
        skuIds.add("C");
        assertEquals(120.0, promotionService.calculateTotalPrice(skuIds));
    }
}
