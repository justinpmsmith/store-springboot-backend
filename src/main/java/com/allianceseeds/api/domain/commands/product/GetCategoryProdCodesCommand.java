package com.allianceseeds.api.domain.commands.product;

import com.allianceseeds.api.domain.commands.Command;
import lombok.Getter;

@Getter
public class GetCategoryProdCodesCommand implements Command {
    private String category;

    public GetCategoryProdCodesCommand(String category) {
        this.category = category;
    }
}