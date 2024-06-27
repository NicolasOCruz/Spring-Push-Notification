package com.nicolascruz.push.api.v1.model.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecord(@NotBlank String firstName,
                         @NotBlank String lastName,
                         @NotBlank @Email String email,
                         @NotBlank
                         String password) {
}
