package com.modoodesigner.web.results;

import com.modoodesigner.domain.model.part.pendant.Pendant;
import com.modoodesigner.web.results.data.PendantData;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

public class PendantResult {

    public static ResponseEntity<ApiResult> created(Pendant pendant) {
        ApiResult apiResult = ApiResult.blank()
                .add("data",new PendantData(pendant));
        return Result.ok(apiResult);
    }
}
