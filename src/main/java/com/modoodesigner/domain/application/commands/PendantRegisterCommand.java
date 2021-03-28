package com.modoodesigner.domain.application.commands;

import com.modoodesigner.domain.model.part.common.Material;
import com.modoodesigner.domain.model.part.common.PlatingColor;
import com.modoodesigner.domain.model.part.pendant.MountingType;
import com.modoodesigner.domain.model.user.UserId;
import com.modoodesigner.utils.IpAddress;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Builder
@Getter @Setter
public class PendantRegisterCommand extends UserCommand {
    private String name;
    private MountingType mountingType;
    private Material material;
    private PlatingColor color;
    private int buyPrice;
    private int stock;
    private String[] tags;
    private Long[] mainImageIndex;
    private Long[] contentImage;
    private String content;

}
