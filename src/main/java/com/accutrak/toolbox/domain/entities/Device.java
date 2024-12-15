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
@Table(name = "device")
public class Device {
    @Id
    public String id;

    @Column(name = "appVersion")
    public String appVersion;

    @Column(name = "insertTime")
    public LocalDateTime lastChange;

    @Column(name = "info",  length = 1000)
    public String info;

    @PrePersist
    public void prePersist() {
        lastChange = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastChange = LocalDateTime.now();
    }

}
