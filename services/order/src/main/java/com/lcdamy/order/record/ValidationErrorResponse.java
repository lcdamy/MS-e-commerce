package com.lcdamy.order.record;

import java.util.Map;

public record ValidationErrorResponse(
        Map<String,String> errors
) {
}
