package com.lcdamy.customer.record;

import java.util.Map;

public record ValidationErrorResponse(
        Map<String,String> errors
) {
}
