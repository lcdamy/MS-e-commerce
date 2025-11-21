package com.lcdamy.product.record;

import java.util.Map;

public record ValidationErrorResponse(
        Map<String,String> errors
) {
}
