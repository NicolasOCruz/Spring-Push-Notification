package com.nicolascruz.push.core.webpush;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Security;

@Configuration
public class BouncyCastleConfig {

    @Bean
    public BouncyCastleProvider bouncyCastleProvider() {
        Security.addProvider(new BouncyCastleProvider());
        return new BouncyCastleProvider();
    }
}
