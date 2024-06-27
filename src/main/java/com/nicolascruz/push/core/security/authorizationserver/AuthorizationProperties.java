package com.nicolascruz.push.core.security.authorizationserver;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Data
@Validated
@ConfigurationProperties("app.auth")
public class AuthorizationProperties {

    @NotBlank
    private String providerUrl;
}
