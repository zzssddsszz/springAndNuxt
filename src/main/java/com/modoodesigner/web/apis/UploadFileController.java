package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.AttachmentService;
import com.modoodesigner.domain.application.commands.AttachmentUploadCommand;
import com.modoodesigner.domain.model.attachment.Attachment;
import com.modoodesigner.domain.model.attachment.FTPUploadFailException;
import com.modoodesigner.domain.model.attachment.ThumbnailCreationException;
import com.modoodesigner.web.results.ApiResult;
import com.modoodesigner.web.results.AttachmentResult;
import com.modoodesigner.web.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class UploadFileController extends AbstractBaseController {
    private final AttachmentService attachmentService;

    @PostMapping("/api/image")
    public ResponseEntity<ApiResult> uploadImage(@RequestParam("file") MultipartFile file,
                                                 HttpServletRequest request) {
        try {
            AttachmentUploadCommand command = new AttachmentUploadCommand(file);
            addTriggeredBy(command, request);
            Attachment upload = attachmentService.imageUpload(command);

            return AttachmentResult.created(upload);
        } catch (ThumbnailCreationException e) {
            String errorMessage = "썸네일 만들기에 실패했습니다.";
            return AttachmentResult.failure(errorMessage);
        } catch (FTPUploadFailException e) {
            String errorMessage = "ftp 업로드에 실패했습니다.";
            return AttachmentResult.failure(errorMessage);
        } catch (IllegalArgumentException e) {
            String errorMessage = "잘못된 접근입니다.";
            return AttachmentResult.failure(errorMessage);
        }catch (IllegalAccessException e) {
            String errorMessage = "이미지 파일이 아닙니다.";
            return AttachmentResult.failure(errorMessage);
        } catch (Exception e) {
            String errorMessage = "파일 업로드에 실패하였습니다.";
            return AttachmentResult.failure(errorMessage);
        }
    }

}
