package com.modoodesigner.web.results.data;

import com.modoodesigner.domain.model.part.pendant.Pendant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class PendantData {
    private Long id;
    private String name;
    private String img;
    private String color;
    private String material;
    private String mountingType;
    private String createdDate;
    private String modifiedDate;
    private int stock;

    public PendantData(Pendant pendant) {
        id = pendant.getId();
        name = pendant.getName();
        img = pendant.getImg();
        color = pendant.getColor().getTypeName();
        stock = pendant.getStock();
        material = pendant.getMaterial().getTypeName();
        mountingType = pendant.getMountingType().getTypeName();
        createdDate = pendant.getCreatedDate().format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss"));
        modifiedDate = pendant.getModifiedDate().format(DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss"));
    }

}
