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
        List<PendantData> pendantDataList = new ArrayList<>();
        pendantList.stream().forEach(pendant->{
            pendantDataList.add(new PendantData(pendant));
        });

        return Result.ok(ApiResult.list(pendantAll,pendantDataList));
    }
}
