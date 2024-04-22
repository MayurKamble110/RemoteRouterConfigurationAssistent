package com.fs.remoterouterconfigurationassistant.api.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatbotApiRequestBody {
    private String username;
    private long deviceId;
    private String text;
}
