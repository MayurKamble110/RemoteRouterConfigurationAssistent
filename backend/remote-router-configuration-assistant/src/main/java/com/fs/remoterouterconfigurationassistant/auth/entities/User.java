package com.fs.remoterouterconfigurationassistant.auth.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User {
    @Id
    @Column(name = "email_id")
    private String emailID;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "password",nullable = false)
    private String password;
}
