package com.lcdamy.customer.record;

import com.lcdamy.customer.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         String id,
         @NotNull(message = "Customer firstname is required")
         String firstname,
         @NotNull(message = "Customer lastname is required")
         String lastname,
         @NotNull(message = "Customer email is required")
         @Email(message = "Customer email is not a valid email")
         String email,
         @NotNull(message = "Address can't be empty")
         Address address

) {
}
