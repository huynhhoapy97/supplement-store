package com.huynhhoapy97;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/*
    Disable Spring Security Auto Configuration by using attribute exclude
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SupplementStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupplementStoreApplication.class, args);
    }
}
