package com.modoodesigner.domain.common.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.FtpSession;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Component
@Slf4j
public class FTPUploader {
    private static String host;
    private static String ftpBasePath;
    private SessionFactory sessionFactory;


    public FTPUploader(@Value("${app.ftp.host}") String ftpHost,
                       @Value("${app.ftp.base-path}") String ftpBasePath,
                       SessionFactory sessionFactory) {
        this.host = ftpHost;
        this.ftpBasePath = ftpBasePath;
        this.sessionFactory = sessionFactory;
    }

    public void upload(TempFile tempImageFile) {


        try {
            Session session = sessionFactory.getSession();
            InputStream inputStream = new FileInputStream(tempImageFile.getFile().getAbsolutePath());
            session.append(inputStream,tempImageFile.getFile().getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }















        /*
        client = new FTPClient();
        try {
            client.setControlEncoding("UTF-8");
            client.connect(ftpHost, NumberUtils.toInt(ftpPort));
//            client.setSoTimeout();
            client.enterLocalPassiveMode();
            client.setFileType(FTP.BINARY_FILE_TYPE);
            int resultCode = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(resultCode)) {
                log.error("FTP 서버 연결 실패했습니다.");
            } else {
                if (!client.login(ftpId, ftpPassword)) {
                    log.error("FTP 로그인 실패했습니다.");
                }
            }
            if (!client.changeWorkingDirectory(ftpBasePath)) {
                log.error("'" + ftpBasePath + "' 폴더가 없습니다.");
            }
            InputStream inputStream = new FileInputStream(tempImageFile.getFile().getAbsolutePath());
            boolean done = client.storeFile(tempImageFile.getFile().getName(), inputStream);
            inputStream.close();
            if (done) {
                log.debug("ftp에 파일 업로드 성공했습니다.");
            } else {
                log.debug("ftp에 파일 업로드 실패했습니다.");
            }

        } catch (Exception e) {
            throw new FTPUploadFailException("ftp에 파일 업로드 실패했습니다.", e);
        } finally {
            if (client.isConnected()) {
                try {
                    client.logout();
                    client.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }*/


    }

    public static String getFtpPath(String filePath) {
        return "https://" + host + "/" + ftpBasePath + "/" + FilenameUtils.getName(filePath);
    }
}
