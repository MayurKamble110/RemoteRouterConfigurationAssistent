package com.fs.remoterouterconfigurationassistant.databases.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "message_history")
public class MessageHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "device_id")
    private long deviceId;

    @Column(name = "username")
    private String username;

    @Column(name = "human_message",length = 5000)
    private String humanMessage;

    @Column(name = "ai_message", length = 5000)
    private String aiMessage;
}
