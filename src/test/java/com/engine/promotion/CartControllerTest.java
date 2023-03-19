package com.engine.promotion;

import com.engine.promotion.model.CartRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void postCalculateTotalPriceAPIShouldReturnTotalPriceAndOkStatus(){
        CartRequest cartRequest = new CartRequest();
        List<String> skuIds = new ArrayList<>();
        skuIds.add("A");
        skuIds.add("A");
        skuIds.add("A");
        skuIds.add("B");
        cartRequest.setSkuIds(skuIds);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CartRequest> cartRequestHttpEntity = new HttpEntity<>(cartRequest, headers);
        ResponseEntity<Double> response = restTemplate.postForEntity("/cart/totalPrice", cartRequestHttpEntity, Double.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(160.0,response.getBody().doubleValue());
    }
}
