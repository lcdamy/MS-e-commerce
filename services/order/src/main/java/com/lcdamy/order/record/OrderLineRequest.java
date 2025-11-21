package com.lcdamy.order.record;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderLineRequest(
    Integer id,
    @NotNull(message = "Product is mandatory")
    Integer orderId,
    @NotNull(message = "Product is mandatory")
    Integer productId,
    @Positive(message = "Quantity must be Positive")
    double quantity
) {
}
