package com.engine.promotion;

import com.engine.promotion.service.FixedPriceNItemsOneSkuPromotion;
import com.engine.promotion.service.PromotionService;
import com.engine.promotion.service.SkuCombinedFixedPricePromotion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations = "/application.yml")
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
    @Test
    public void calculateTotalPriceWithAllSku() {
        List<String> skuIds = new ArrayList<>();
        skuIds.add("A");
        skuIds.add("B");
        skuIds.add("C");
        skuIds.add("D");
        assertEquals(110.0, promotionService.calculateTotalPrice(skuIds));
    }
    @Test
    public void calculateTotalPriceWithMultipleSkuItems() {
        List<String> skuIds = new ArrayList<>();
        skuIds.add("A");
        skuIds.add("A");
        skuIds.add("A");
        skuIds.add("B");
        skuIds.add("C");
        skuIds.add("D");
        assertEquals(190.0, promotionService.calculateTotalPrice(skuIds));
    }

    @Test
    public void calculateTotalPriceWithNoPromotionsScenarioOne() {
        List<String> skuIds = new ArrayList<>();
        skuIds.add("A");
        skuIds.add("B");
        skuIds.add("C");
        assertEquals(100.0, promotionService.calculateTotalPrice(skuIds));
    }
    @Test
    public void calculateTotalPriceWithMultipleSkuItemsScenarioTwo() {
        List<String> skuIds = new ArrayList<>();
        skuIds.add("A");
        skuIds.add("A");
        skuIds.add("A");
        skuIds.add("A");
        skuIds.add("A");
        skuIds.add("B");
        skuIds.add("B");
        skuIds.add("B");
        skuIds.add("B");
        skuIds.add("B");
        skuIds.add("C");
        assertEquals(370.0, promotionService.calculateTotalPrice(skuIds));
    }

    @Test
    public void calculateTotalPriceWithMultipleSkuItemsScenarioThree() {
        List<String> skuIds = new ArrayList<>();
        skuIds.add("A");
        skuIds.add("A");
        skuIds.add("A");
        skuIds.add("B");
        skuIds.add("B");
        skuIds.add("B");
        skuIds.add("B");
        skuIds.add("B");
        skuIds.add("C");
        skuIds.add("D");
        assertEquals(280.0, promotionService.calculateTotalPrice(skuIds));
    }

    @Test
    public void calculateTotalPriceWithFixedNSkuItemsOfOneSkuType() {
        List<String> skuIds = new ArrayList<>();
        skuIds.add("A");
        skuIds.add("A");
        skuIds.add("A");
        assertEquals(130.0, promotionService.calculateTotalPrice(skuIds));
    }

    @Test
    public void calculateTotalPriceWithFixedNSkuItemsOfSecondSkuType() {
        List<String> skuIds = new ArrayList<>();
        skuIds.add("B");
        skuIds.add("B");
        assertEquals(45.0, promotionService.calculateTotalPrice(skuIds));
    }

    @Test
    public void calculateTotalPriceWithCombinedSkuType() {
        List<String> skuIds = new ArrayList<>();
        skuIds.add("C");
        skuIds.add("D");
        assertEquals(30.0, promotionService.calculateTotalPrice(skuIds));
    }
}
