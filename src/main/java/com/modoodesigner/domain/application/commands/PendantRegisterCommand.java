package com.modoodesigner.domain.application.commands;

import com.modoodesigner.domain.model.part.common.Material;
import com.modoodesigner.domain.model.part.common.PlatingColor;
import com.modoodesigner.domain.model.part.pendant.MountingType;
import com.modoodesigner.domain.model.user.UserId;
import com.modoodesigner.utils.IpAddress;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Builder
@Getter @Setter
public class PendantRegisterCommand extends UserCommand {
    private String name;
    private MountingType mountingType;
    private Material material;
    private PlatingColor color;
    private int buyPrice;
    private int stock;
    private List<String> tags;
    private List<Long> mainImages;
    private List<Long> contentImages;
    private String content;

}
