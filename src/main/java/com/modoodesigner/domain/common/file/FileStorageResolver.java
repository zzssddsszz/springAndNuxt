package com.modoodesigner.domain.common.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FileStorageResolver {

    private String activeStorageName;
    private final ApplicationContext applicationContext;

    public FileStorageResolver(@Value("${app.file-storage.active}")String activeStorageName, ApplicationContext applicationContext) {
        this.activeStorageName = activeStorageName;
        this.applicationContext = applicationContext;
    }

    public FileStorage resolve() { return applicationContext.getBean(activeStorageName, FileStorage.class);}


}
