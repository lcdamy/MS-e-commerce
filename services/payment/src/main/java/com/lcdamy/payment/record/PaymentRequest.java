package com.lcdamy.payment.record;

import com.lcdamy.payment.model.PaymentMethod;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        @NotNull(message = "Amount can't be empty")
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
