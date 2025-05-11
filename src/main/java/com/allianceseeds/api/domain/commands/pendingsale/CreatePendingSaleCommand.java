package com.allianceseeds.api.domain.commands.pendingsale;

import com.allianceseeds.api.domain.commands.Command;
import lombok.Getter;

@Getter
public class CreatePendingSaleCommand implements Command {
    private String paymentId;
    private String name;
    private String address;
    private Float totalPrice;
    private Long shipping_fee;
    private String prodCodes; // JSON stringified list of product codes
    private String email;
    private String cell;

    public CreatePendingSaleCommand(String paymentId, String name, String address, Float totalPrice,
                                    Long shipping_fee, String prodCodes, String email, String cell) {
        this.paymentId = paymentId;
        this.name = name;
        this.address = address;
        this.totalPrice = totalPrice;
        this.shipping_fee = shipping_fee;
        this.prodCodes = prodCodes;
        this.email = email;
        this.cell = cell;
    }
}