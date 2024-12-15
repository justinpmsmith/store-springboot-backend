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
@Table(name = "log",uniqueConstraints = {

        // uniqueness on the deviceId/fileName pair
        @UniqueConstraint(columnNames = {"deviceId", "fileName"})
})
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name="hash", unique = true)
    public String hash;

    @Column(name = "deviceId")
    public String deviceId;

    @Column(name = "lastModified")
    public  int lastModified;

    @Column(name = "fileName")
    public String fileName;

    @UpdateTimestamp
    @Column(name = "insertTime")
    public LocalDateTime insertTime;

    @Column(name = "data", columnDefinition = "text")
    public String data;

    @PrePersist
    public void prePersist() {
        insertTime = LocalDateTime.now();
    }


}
