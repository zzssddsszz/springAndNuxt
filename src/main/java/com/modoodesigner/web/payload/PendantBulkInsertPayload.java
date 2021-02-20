package com.modoodesigner.web.payload;

import com.modoodesigner.domain.application.commands.PendantBulkRegisterCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PendantBulkInsertPayload {

    private String name;

    public PendantBulkRegisterCommand toCommand() {
        PendantBulkRegisterCommand command = PendantBulkRegisterCommand.builder().build();
        return command;
    }
}
