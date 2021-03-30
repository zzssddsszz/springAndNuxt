package com.modoodesigner.domain.common.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Component
@Slf4j
@Profile({"dev","real"})
public class FTPSeverUploader implements FTPUploader {
    private static String host;
    private static String ftpBasePath;
    private SessionFactory sessionFactory;

    public FTPSeverUploader(@Value("${app.ftp.host}") String ftpHost,
                            @Value("${app.ftp.base-path}") String ftpBasePath,
                            SessionFactory sessionFactory) {
        this.host = ftpHost;
        this.ftpBasePath = ftpBasePath;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void upload(TempFile tempImageFile) {
        Session session = sessionFactory.getSession();
        try {
            InputStream inputStream = new FileInputStream(tempImageFile.getFile().getAbsolutePath());
            if (!session.exists(ftpBasePath)) {
                session.mkdir(ftpBasePath);
            }
            session.append(inputStream, ftpBasePath + tempImageFile.getFile().getName());
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    public static String getFtpPath(String filePath) {
        return "https://" + host + "/" + ftpBasePath + "/" + FilenameUtils.getName(filePath);
    }
}