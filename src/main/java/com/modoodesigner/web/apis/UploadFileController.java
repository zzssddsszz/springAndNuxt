package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.AttachmentService;
import com.modoodesigner.domain.application.commands.AttachmentUploadCommand;
import com.modoodesigner.domain.model.attachment.Attachment;
import com.modoodesigner.web.results.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class UploadFileController extends AbstractBaseController{
    private final AttachmentService attachmentService;

    @PostMapping("/api/image")
    public ResponseEntity<ApiResult> uploadImage(@RequestParam("file")MultipartFile file,
                                                 HttpServletRequest request){
        try {
            AttachmentUploadCommand command = new AttachmentUploadCommand(file);
            addTriggeredBy(command, request);
            Attachment upload = attachmentService.upload(command);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
