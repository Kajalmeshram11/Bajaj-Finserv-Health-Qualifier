package com.bajaj.qualifier1.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bajaj.qualifier1.model.WebhookResponse;
import com.bajaj.qualifier1.config.SqlQuerySolver;

import java.util.HashMap;
import java.util.Map;

@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void generateWebhookAndSubmitQuery() {
        try {
            // Step 1: Generate webhook
            String generateWebhookUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("name", "Nishu Kumari");
            requestBody.put("regNo", "REG12346"); // your reg number
            requestBody.put("email", "kumarinishu407@gmail.com");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

            System.out.println("🚀 Sending POST request to generate webhook...");
            ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(
                    generateWebhookUrl, requestEntity, WebhookResponse.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                String webhookUrl = response.getBody().getWebhook();
                String accessToken = response.getBody().getAccessToken();

                System.out.println("✅ Webhook generated successfully!");
                System.out.println("📩 Webhook URL: " + webhookUrl);
                System.out.println("🔑 Access Token: " + accessToken);

                // Step 2: Solve SQL problem based on regNo
                String finalQuery = SqlQuerySolver.solveSqlProblem("REG12346");

                // Step 3: Send SQL query to webhook
                sendFinalQuery(webhookUrl, accessToken, finalQuery);
            } else {
                System.out.println("⚠️ Failed to generate webhook. Response code: " + response.getStatusCode());
            }

        } catch (Exception e) {
            System.err.println("❌ Exception while generating webhook: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void sendFinalQuery(String webhookUrl, String accessToken, String finalQuery) {
        try {
            System.out.println("\n🚀 Sending SQL query to webhook...");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("finalQuery", finalQuery);

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    webhookUrl, requestEntity, String.class
            );

            System.out.println("✅ Query submission completed!");
            System.out.println("🧾 Response Code: " + response.getStatusCodeValue());
            System.out.println("📜 Response Body: " + response.getBody());
        } catch (Exception e) {
            System.err.println("❌ Exception while sending final query: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
