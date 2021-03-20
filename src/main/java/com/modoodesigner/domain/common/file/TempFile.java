package com.modoodesigner.domain.common.file;

import lombok.Getter;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
public class TempFile {

    private String rootTempPath;
    private String fileRelativePath;

    public static TempFile create(String rootTempPath, Path fileAbsolutePath) {
        TempFile tempFile = new TempFile();
        tempFile.rootTempPath = rootTempPath;
        tempFile.fileRelativePath = Paths.get(rootTempPath).toAbsolutePath().relativize(fileAbsolutePath).toString();
        return tempFile;
    }

    public File getFile() { return new File(rootTempPath + "/" + fileRelativePath); }

}
