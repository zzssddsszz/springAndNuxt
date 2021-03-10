package com.modoodesigner.domain.common.file;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileStorageResolver {

    @Value("${app.file-storage.active}")
    private String activeStorageName;
    private final ApplicationContext applicationContext;

    public FileStorage resolve() { return applicationContext.getBean(activeStorageName, FileStorage.class);}


}
