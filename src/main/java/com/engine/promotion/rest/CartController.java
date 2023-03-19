package com.engine.promotion.rest;

import com.engine.promotion.model.CartRequest;
import com.engine.promotion.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping("/cart/totalPrice")
    public ResponseEntity<Double> calculateTotalPrice(@RequestBody CartRequest cartRequest) {
        double totalPrice = promotionService.calculateTotalPrice(cartRequest.getSkuIds());
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }
}
