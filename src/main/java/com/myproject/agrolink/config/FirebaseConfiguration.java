package com.myproject.agrolink.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class FirebaseConfiguration {

    @PostConstruct
    public void initializeFirebase() throws IOException {
        FirebaseInitializer.initialize();
    }
}
