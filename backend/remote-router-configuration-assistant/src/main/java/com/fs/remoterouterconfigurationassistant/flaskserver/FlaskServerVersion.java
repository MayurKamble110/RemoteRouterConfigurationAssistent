package com.fs.remoterouterconfigurationassistant.flaskserver;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
import com.fs.remoterouterconfigurationassistant.api.model.RouterInterfaceResponceDto;
import com.fs.remoterouterconfigurationassistant.api.model.RouterVersionResponseDto;

public class FlaskServerVersion {
    public static final String flaskServerApiEndpoint = "http://localhost:5000/ask-ai";
    public static RouterVersionResponseDto makeRequest(FlaskServerApiRequestBody body) {

        body.setText(body.getText()+body.getPrompt());
        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        try {
            String jsonBody = objectMapper.writeValueAsString(body);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                    flaskServerApiEndpoint,
                    requestEntity,
                    String.class);
            System.out.println(responseEntity.getBody());
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                String responseBody = responseEntity.getBody()
                        .replaceAll("```", "")
                        .replaceAll("JSON", "")
                        .replaceAll("json", "");
                System.out.println("Parsed responce..... \n"+responseBody);
                ObjectMapper mapper = new ObjectMapper();
                RouterVersionResponseDto routerVersionResponseDto =
                        mapper.readValue(responseBody, RouterVersionResponseDto.class);
                return routerVersionResponseDto;
            } else {
                System.err.println("Failed to get a successful response from the server.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
