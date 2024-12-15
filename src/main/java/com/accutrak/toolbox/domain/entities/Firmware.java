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
@Table(name = "firmware", uniqueConstraints = {

        // uniqueness on the site/hash pair
        @UniqueConstraint(columnNames = {"site", "hash"})
}) // uniqueness on hash and site handled in fw handler
public class Firmware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "fileName")
    public String fileName; // including extension

    @Column(name = "hardware")
    public String hardware;

    @Column(name = "hash")
    public String hash;

    @UpdateTimestamp
    @Column(name = "lastChange")
    public LocalDateTime insertTime;

    @Column(name = "effectiveFrom")
    public int effectiveFrom;

    @Column(name = "effectiveTo")
    public int effectiveTo;

    @Column(name = "site")
    public String site;

    @Column(name = "blob", columnDefinition = "text")
    public String blob;

    @PrePersist
    public void prePersist() {
        insertTime = LocalDateTime.now();
    }

}
