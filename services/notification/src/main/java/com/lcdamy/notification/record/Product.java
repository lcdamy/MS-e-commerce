package com.lcdamy.notification.record;

import java.math.BigDecimal;

public record Product(
        Integer ProductId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
