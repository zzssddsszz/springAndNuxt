package com.modoodesigner.domain.model.attachment;

public class ThumbnailCreationException extends RuntimeException {


    private static final long serialVersionUID = -5603162867394467697L;

    public ThumbnailCreationException(String message) {
        super(message);
    }

    public ThumbnailCreationException(String message, Throwable cause) {
        super(message,cause);
    }
}
