package com.lcdamy.customer.record;

import com.lcdamy.customer.model.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
