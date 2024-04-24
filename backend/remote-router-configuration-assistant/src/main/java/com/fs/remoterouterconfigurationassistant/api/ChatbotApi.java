package com.fs.remoterouterconfigurationassistant.api;

import com.fs.remoterouterconfigurationassistant.api.model.ChatbotDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/routers")
public class ChatbotApi {
    @Autowired
    private ChatbotApiService delegate;

    @PostMapping("/{device_id}/chat-bot")
    public ResponseEntity<String> askChatbot(@RequestBody ChatbotDto chatbotDto) {
        try {
            return ResponseEntity.ok(delegate.askChatBot(chatbotDto));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
