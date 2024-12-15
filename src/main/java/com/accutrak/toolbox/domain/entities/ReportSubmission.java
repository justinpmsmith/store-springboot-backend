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
@Table(name = "reportSubmission")
public class ReportSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "deviceId")
    String deviceId;

    @Column(name = "uuid")
    String uuid;

    @Column(name = "scriptId")
    String scriptId;

    @UpdateTimestamp
    @Column(name = "insertTime")
    public LocalDateTime insertTime;

    @Column(name = "submissionTime")
    int submissionTime;

    @Column(name = "submissionId")
    String submissionId;

    @Column(name = "response", columnDefinition = "text")
    public String response;

    @Column(name = "site")
    public String site;

    @PrePersist
    public void prePersist() {
        insertTime = LocalDateTime.now();
    }



}
