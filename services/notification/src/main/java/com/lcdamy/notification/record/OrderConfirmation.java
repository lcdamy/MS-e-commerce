package com.lcdamy.notification.record;

import com.lcdamy.notification.model.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
         String orderReference,
         BigDecimal totalAmount,
         PaymentMethod paymentMethod,
         Customer customer,
         List<Product> products
) {
}
