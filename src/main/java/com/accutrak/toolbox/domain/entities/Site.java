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
@Table(name = "site")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto inc
    public Long id;

    @Column(name = "name", unique=true)
    public String name;

    @UpdateTimestamp
    @Column(name = "insertTime")
    public LocalDateTime insertTime;

    @Column(name = "enabled")
    public Boolean enabled = true;

    @PrePersist
    public void prePersist() {
        insertTime = LocalDateTime.now();
    }


}
