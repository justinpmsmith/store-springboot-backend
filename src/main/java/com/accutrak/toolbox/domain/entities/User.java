package com.accutrak.toolbox.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"User\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto inc
    public Long id;

    public int otp;

    @Column(name = "deviceId")
    public String deviceId;

    @Column(name = "uuid", unique=true)
    public String uuid;

    @Column(name = "name")
    public String name;

    @Column(name = "surname")
    public String surname;

    @Column(name = "email")
    public String email;

    @Column(name = "site")
    public String site;

    @Column(name = "tagConfigLevel")
    public int tagConfigLevel = 3;

    @Column(name = "lastChange")
    public LocalDateTime lastChange;

    @PrePersist
    public void generateUuid() {
        uuid = UUID.randomUUID().toString();
        lastChange = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastChange = LocalDateTime.now();
    }
}