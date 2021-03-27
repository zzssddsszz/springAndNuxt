package com.modoodesigner.domain.model.attachment;

public class FTPUploadFailException extends RuntimeException {

    private static final long serialVersionUID = -8885726045140701857L;

    public FTPUploadFailException(String message) {
        super(message);
    }

    public FTPUploadFailException(String message, Throwable cause) {
        super(message,cause);
    }
}
