package com.allianceseeds.api.domain.commands.product;

import com.allianceseeds.api.domain.commands.Command;
import lombok.Getter;

@Getter
public class GetProductByProdCodeCommand implements Command {
    String prodCode;

    public GetProductByProdCodeCommand(String prodCode) {
        this.prodCode = prodCode;
    }
}
