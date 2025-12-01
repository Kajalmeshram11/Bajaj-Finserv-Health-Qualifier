package com.bajaj.qualifier1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bajaj.qualifier1.service.WebhookService;

@SpringBootApplication
public class Qualifier1Application implements CommandLineRunner {

    @Autowired
    private WebhookService webhookService;

    public static void main(String[] args) {
        SpringApplication.run(Qualifier1Application.class, args);
    }

    @Override
    public void run(String... args) {
        webhookService.generateWebhookAndSubmitQuery();
    }
}
