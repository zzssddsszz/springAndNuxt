package com.modoodesigner.web.results;

import com.modoodesigner.domain.model.attachment.Attachment;
import com.modoodesigner.web.results.data.AttachmentData;
import org.springframework.http.ResponseEntity;

public class AttachmentResult {
    public static ResponseEntity<ApiResult> created(Attachment upload) {
        ApiResult apiResult = ApiResult.blank()
                .add("data", new AttachmentData(upload));
        return Result.ok(apiResult);
    }
}
