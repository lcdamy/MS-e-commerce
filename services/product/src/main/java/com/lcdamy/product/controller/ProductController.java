package com.lcdamy.product.controller;

import com.lcdamy.product.record.ProductPurchaseRequest;
import com.lcdamy.product.record.ProductPurchaseResponse;
import com.lcdamy.product.record.ProductRequest;
import com.lcdamy.product.record.ProductResponse;
import com.lcdamy.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest productRequest){
        return  ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(@RequestBody List<ProductPurchaseRequest> productRequest){
        return ResponseEntity.ok(productService.purchaseProducts(productRequest));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable("product-id") Integer productId){
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProduct(){
        return ResponseEntity.ok(productService.findAllProduct());
    }
}
