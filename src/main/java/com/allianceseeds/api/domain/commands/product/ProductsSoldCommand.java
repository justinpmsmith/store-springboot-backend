package com.allianceseeds.api.domain.commands.product;

import com.allianceseeds.api.domain.entities.Product;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductsSoldCommand implements ProductCommand{
    private String name;
    private String address;
    private Float totalPrice;
    private Long shipping_fee;
    private List<Product> products;
    private String email;
    private String cell;

    public ProductsSoldCommand(String name, String address, float totalPrice, Long shipping_fee, List<Product> products, String email, String cell) {
        this.name = name;
        this.address = address;
        this.totalPrice = totalPrice;
        this.shipping_fee = shipping_fee;
        this.products = products;
        this.email = email;
        this.cell = cell;
    }
}
