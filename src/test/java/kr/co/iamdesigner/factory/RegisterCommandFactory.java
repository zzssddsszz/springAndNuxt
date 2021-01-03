package kr.co.iamdesigner.factory;

import kr.co.iamdesigner.domain.application.commands.ChainRegisterCommand;
import kr.co.iamdesigner.domain.application.commands.PendantRegisterCommand;
import kr.co.iamdesigner.domain.model.part.common.Material;
import kr.co.iamdesigner.domain.model.part.common.PlatingColor;
import kr.co.iamdesigner.domain.model.part.pendant.MountingType;

public final class RegisterCommandFactory {
    public static PendantRegisterCommand getPendantCommand(){
        PendantRegisterCommand command = PendantRegisterCommand.builder()
                .name("팬던트이름")
                .code("팬던트코드")
                .buyPrice(1000)
                .material(Material.SILVER)
                .color(PlatingColor.BLACK)
                .mountingType(MountingType.SINGLE)
                .stock(20)
                .build();
        return command;
    }

    public static ChainRegisterCommand getChainCommand() {
        ChainRegisterCommand command = ChainRegisterCommand.builder()
                .name("체인이름")
                .code("체인코드")
                .buyPrice(1000)
                .material(Material.SILVER)
                .color(PlatingColor.NONE)
                .stock(200)
                .build();
        return command;


    }
}
