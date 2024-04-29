package com.fs.remoterouterconfigurationassistant.flaskserver;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fs.remoterouterconfigurationassistant.api.model.FlaskServerApiRequestBody;
import com.fs.remoterouterconfigurationassistant.api.model.RouterInterfaceResponceDto;
import com.fs.remoterouterconfigurationassistant.api.model.RouterVersionResponseDto;
import com.fs.remoterouterconfigurationassistant.api.model.ShowAccessModeDto;
import com.fs.remoterouterconfigurationassistant.api.model.ShowInventoryDto;

public class FlaskServer {

    public static final String flaskServerApiEndpoint = "http://localhost:5000/ask-ai";

    public static RouterInterfaceResponceDto getRouterInterfaceResponceDto(FlaskServerApiRequestBody body)
                    throws  BadRequestException {
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

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                String responseBody = responseEntity.getBody()
                                .replaceAll("```", "")
                                .replaceAll("JSON", "")
                                .replaceAll("json", "");
                ObjectMapper mapper = new ObjectMapper();
                RouterInterfaceResponceDto routerInterfaceResponseDto =
                                mapper.readValue(responseBody, RouterInterfaceResponceDto.class);

                return routerInterfaceResponseDto;
            } else {
                System.err.println("Failed to get a successful response from the server.");
            }

        } catch (Exception e) {
            throw new BadRequestException("Server is not responding...");
        }
        System.out.println("Error...");
        return null;
    }

    public static RouterVersionResponseDto getRouterVersionResponseDto(FlaskServerApiRequestBody body) {
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

    public static ShowAccessModeDto getAccessMode(FlaskServerApiRequestBody body) {
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
                ShowAccessModeDto showAccessModeDto =
                        mapper.readValue(responseBody, ShowAccessModeDto.class);
                return showAccessModeDto;
            } else {
                System.err.println("Failed to get a successful response from the server.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static ShowInventoryDto getShowInventoryDto(FlaskServerApiRequestBody body) {
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
                ShowInventoryDto showInventoryDto =
                        mapper.readValue(responseBody, ShowInventoryDto.class);
                return showInventoryDto;
            } else {
                System.err.println("Failed to get a successful response from the server.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static String analyseRouter(Long deviceId) throws BadRequestException, ResourceAccessException{
        String ANALYSE_ROUTER_ENDPOINT = "http://localhost:5000/analyse-router";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\"device_id\": \"" + deviceId + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                    ANALYSE_ROUTER_ENDPOINT,
                    requestEntity,
                    String.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                if(responseEntity.getBody().equals("NO_DEVICE"))
                    throw new BadRequestException("Device not found");
                else if(responseEntity.getBody().equals("NO_DATA"))
                    throw new BadRequestException("Data not found");
                return responseEntity.getBody();
            } else {
                System.out.println("Failed to get a successful response from the server.");
                return "NULL";
            }
        } catch (ResourceAccessException e) {
            throw new ResourceAccessException("Server is not responding");
        }
    }

    public static String analyseInterface(Long interfaceId) throws BadRequestException,ResourceAccessException {
        String ANALYSE_ROUTER_ENDPOINT = "http://localhost:5000/analyse-interface";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\"interface_id\": \"" + interfaceId + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                    ANALYSE_ROUTER_ENDPOINT,
                    requestEntity,
                    String.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                if(responseEntity.getBody().equals("null"))
                    throw new BadRequestException("Interface not found");
                return responseEntity.getBody();
            } else {
                System.out.println("Failed to get a successful response from the server.");
                return "NULL";
            }
        } catch (ResourceAccessException e) {
            throw new ResourceAccessException("Server is not responding");
        }
    }

    public static String getParsedCpuProcessHistoryData(FlaskServerApiRequestBody body) throws JsonParseException, ResourceAccessException {
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

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                String responseBody = responseEntity.getBody()
                                .replaceAll("```", "")
                                .replaceAll("JSON", "")
                                .replaceAll("json", "");
                System.out.println(responseBody);
                return responseBody;
            }
        } catch(JsonProcessingException e){
            throw new JsonParseException("Json Parsing failed.");
        } catch (ResourceAccessException e) {
            throw new ResourceAccessException("Server is not responding.");
        }
        return "0";
    }
}
