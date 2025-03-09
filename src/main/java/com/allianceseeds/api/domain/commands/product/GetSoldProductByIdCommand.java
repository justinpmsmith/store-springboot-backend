package com.allianceseeds.api.domain.commands.product;

import lombok.Getter;

@Getter
public class GetSoldProductByIdCommand implements ProductCommand {
    private Long id;

    public GetSoldProductByIdCommand(Long id) {
        this.id = id;
    }
}
