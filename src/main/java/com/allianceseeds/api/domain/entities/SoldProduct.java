package com.allianceseeds.api.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sold_product")
public class SoldProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @CreationTimestamp
    @Column(name = "dateSold")
    public LocalDateTime dateSold;

    @Column(name = "prodCode")
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

    @Column(name = "customerName")
    public String customerName;

    @Column(name = "customerEmail")
    public String customerEmail;

    @Column(name = "customerAddress", columnDefinition = "text")
    public String customerAddress;
}