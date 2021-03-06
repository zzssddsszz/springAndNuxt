package com.modoodesigner.web.payload;

import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.model.part.common.Material;
import com.modoodesigner.domain.model.part.common.PlatingColor;
import com.modoodesigner.domain.model.part.pendant.MountingType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PendantRegistrationPayload {

    private String name;
    private String mountingType;
    private String material;
    private String color;
    private int buyPrice;
    private int stock;
    private List<String> tags;
    private List<Long> mainImages;
    private String content;

    public PendantRegisterCommand toCommand() {
        PendantRegisterCommand command = PendantRegisterCommand.builder()
                .name(name)
                .mountingType(MountingType.findByTypeName(mountingType))
                .material(Material.findByTypeName(material))
                .color(PlatingColor.findByTypeName(color))
                .buyPrice(buyPrice)
                .stock(stock)
                .tags(tags)
                .mainImages(mainImages)
                .build();
        return command;
    }

    /*{
    "name": "이름",
    "mountingType": "",
    "material": "",
    "color": "무도금",
    "buyPrice": "54365346",
    "stock": "567564",
    "tags": [],
    "mainImageIndex": [
        118,
        117,
        119
    ],
    "contentImage": [
        122
    ],
    "content": "<p><img src=\"https://modoodesigner.speedgabia.com/test/app/image/public//1616917757641.714f1e34-6258-4963-ade2-808ad88bd72e.jpg\" alt=\"\" width=\"1332\" height=\"1055\" /></p>"
}*/

}
