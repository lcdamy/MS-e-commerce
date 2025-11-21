package com.lcdamy.product.service;

import com.lcdamy.product.exception.ProductPurchaseException;
import com.lcdamy.product.record.ProductPurchaseRequest;
import com.lcdamy.product.record.ProductPurchaseResponse;
import com.lcdamy.product.record.ProductRequest;
import com.lcdamy.product.record.ProductResponse;
import com.lcdamy.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(@Valid ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productRequest) {
        var  productIds = productRequest
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        var storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if(productIds.size() != storedProducts.size()){
            throw new ProductPurchaseException("One or more product doesn't exist");
        }

        var storedRequest = productRequest
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchaseProducts = new ArrayList<ProductPurchaseResponse>();

        for(int i = 0; i < storedProducts.size(); i++){
            var product = storedProducts.get(i);
            var productRequested = storedRequest.get(i);

            if(product.getAvailableQuantity() < productRequested.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: %s "+product.getId());
            }

            var  newAvailableQuantity = product.getAvailableQuantity() - productRequested.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchaseProducts.add(productMapper.toProductPurchaseResponse(product,productRequested.quantity()));
        }
        return purchaseProducts;
    }

    public ProductResponse findProductById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID:: %s " + productId));
    }

    public List<ProductResponse> findAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
