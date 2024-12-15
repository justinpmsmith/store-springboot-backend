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
@Table(name = "reportScript")
public class ReportScript {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "alias")
    String alias;

    @Column(name = "version")
    String version;

    @Column(name = "effectiveFrom")
    int effectiveFrom;

    @Column(name = "effectiveTo")
    int effectiveTo;

    @Column(name = "site")
    String site;

    @Column(name = "scriptId", unique=true)
    String scriptId;

    @Column(name = "script", columnDefinition = "text")
    String script;

    @Column(name = "insertTime")
    public LocalDateTime insertTime;

    @Column(name = "theme", columnDefinition = "text")
    String theme;

    @PrePersist
    public void prePersist() {
        insertTime = LocalDateTime.now();
    }


}
