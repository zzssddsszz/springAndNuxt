package com.modoodesigner.domain.common.file;

import java.io.IOException;

public class FileStorageException extends RuntimeException {

    private static final long serialVersionUID = 2136499834202262942L;

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message,cause);
    }
}
