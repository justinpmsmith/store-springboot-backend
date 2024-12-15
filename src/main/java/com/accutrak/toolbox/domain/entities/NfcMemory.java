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
@Table(name = "nfcMemory")
public class NfcMemory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "uuid")
    public String uuid;

    @Column(name = "deviceId")
    public String deviceId;

    @Column(name = "serial")
    public String serial;

    @Column(name = "scanTimestamp")
    public int scanTimestamp;

    @Column(name = "insertTime")
    public LocalDateTime insertTime;

    @Column(name = "jsonData", columnDefinition = "TEXT")
    public String jsonData;

    @PrePersist
    public void prePersist() {
        insertTime = LocalDateTime.now();
    }


}
