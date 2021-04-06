/*
package com.modoodesigner.web.results;

import com.modoodesigner.domain.model.part.pendant.Pendant;
import com.modoodesigner.web.results.data.PendantData;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PendantListResult {

    public static ResponseEntity<ApiResult> created(Page<Pendant> pendantAll) {

        List<Pendant> pendantList = pendantAll.getContent();
        List<PendantListData> pendantDataList = new ArrayList<>();
        pendantList.stream().forEach(pendant->{
            pendantDataList.add(new PendantListData(pendant));
        });

        return Result.ok(ApiResult.list(pendantAll,pendantDataList));
    }

    @Getter
    @Setter
    static class PendantListData {
        private Long id;
        private String name;
        private String img;
        private String color;
        private String material;
        private String mountingType;
        private String createdDate;
        private String modifiedDate;
        private int stock;

        public PendantListData(Pendant pendant) {
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

}
*/
