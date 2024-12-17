package com.allianceseeds.api.domain.entities;

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
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @UpdateTimestamp
    @Column(name = "insertTime")
    public LocalDateTime insertTime;

    @Column(name = "prodCode", unique = true)
    public String prodCode;

    @Column(name = "category")
    public String category;

    @Column(name = "name")
    public String name;

    @Column(name = "photos", columnDefinition = "text")
    public String photos;

    @Column(name = "price")
    public float price;

    @Column(name = "document", columnDefinition = "text")
    public String document;

    @Column(name = "info", columnDefinition = "text")
    public String info;

    @Column(name = "quantity")
    public long quantity;


}
