package com.fs.remoterouterconfigurationassistant.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fs.remoterouterconfigurationassistant.api.model.ChatbotApiRequestBody;
import com.fs.remoterouterconfigurationassistant.api.model.ChatbotDto;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatbotApiService {
    public final String ENDPOINT = "http://localhost:5000/chat-bot";


    public String askChatBot(ChatbotDto chatbotDto) throws JsonProcessingException {
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        ChatbotApiRequestBody chatbotApiRequestBody=ChatbotApiRequestBody.builder()
                .deviceId(chatbotDto.getDeviceId())
                .username(chatbotDto.getUsername())
                .text(chatbotDto.getUserQuery())
                .build();
        String requestBody = objectMapper.writeValueAsString(chatbotApiRequestBody);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        try{
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                    ENDPOINT,
                    requestEntity,
                    String.class
            );
            if(responseEntity.getStatusCode().is2xxSuccessful())
                return responseEntity.getBody();
            return responseEntity.getStatusCode().toString();
        }catch (ResourceAccessException e){
            throw new ResourceAccessException("Server is not responding");
        }
    }
}
