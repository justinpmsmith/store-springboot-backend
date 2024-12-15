package com.accutrak.toolbox.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Software")
public class Software {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "fileName")
    public String fileName; // including extension

    @Column(name = "versionCode")
    public int versionCode;

    @Column(name = "hash", unique = true)
    public String hash;

    @Column(name = "releaseDate")
    public int releaseDate;

    @Column(name = "effectiveFrom")
    public int effectiveFrom;

    @Column(name = "effectiveTo")
    public int effectiveTo;

    @Column(name = "blob", columnDefinition = "text")
    public String blob;

    @Column(name = "site")
    public String site;

}

