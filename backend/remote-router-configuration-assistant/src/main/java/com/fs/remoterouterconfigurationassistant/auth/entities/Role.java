package com.fs.remoterouterconfigurationassistant.auth.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(value = RoleID.class)
@Table(name = "roles")
@Builder
public class Role {
    @Id
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.DETACH,
    CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "email_id")
    private User user;

    @Id
    @Column(name = "role")
    private String role;
}
