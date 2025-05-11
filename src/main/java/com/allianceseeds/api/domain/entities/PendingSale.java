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
@Table(name = "pending_sale")
public class PendingSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @CreationTimestamp
    @Column(name = "insertTime")
    public LocalDateTime insertTime;

    @Column(name = "paymentId", unique = true)
    public String paymentId;

    @Column(name = "prodCodes", columnDefinition = "text")
    public String prodCodes; // JSON stringified list of product codes

    @Column(name = "name")
    public String name;

    @Column(name = "address", columnDefinition = "text")
    public String address;

    @Column(name = "totalPrice")
    public Float totalPrice;

    @Column(name = "shipping_fee")
    public Long shipping_fee;

    @Column(name = "email")
    public String email;

    @Column(name = "cell")
    public String cell;
}