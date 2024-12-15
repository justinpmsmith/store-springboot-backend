package com.accutrak.toolbox.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "otp")
public class Otp {
    @Id
    @Column(name = "email")
    public String email;

    @Column(name = "deviceId")
    public String deviceId;

    @Column(name = "otp")
    public int otp;

    @Column(name = "expiration")
    public long expiration;

    @Column(name = "notificationSent")
    public Boolean notificationSent = false;

    @Column(name = "lastChange")
    public LocalDateTime lastChange;

    public Otp(String email, String deviceId,  int otp, long expiration) {
        this.email = email;
        this.deviceId = deviceId;
        this.otp = otp;
        this.expiration = expiration;
        this.notificationSent = false;
    }

    @PrePersist
    public void prePersist() {
        lastChange = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastChange = LocalDateTime.now();
    }

}
