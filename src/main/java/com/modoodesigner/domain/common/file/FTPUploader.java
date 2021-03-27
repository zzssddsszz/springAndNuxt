package com.modoodesigner.domain.common.file;

import com.modoodesigner.domain.model.attachment.ImageProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.SocketException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@Slf4j
public class FTPUploader {
    private Environment environment;
    private ImageProcessor imageProcessor;
    private static String ftpHost;
    private String ftpPort;
    private static String ftpBasePath;
    private String ftpId;
    private String ftpPassword;
    private FTPClient client;

    @PostConstruct
    public void init(){
        client = new FTPClient();
        try {
            client.setControlEncoding("UTF-8");
            client.connect(ftpHost, NumberUtils.toInt(ftpPort));
            int resultCode = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(resultCode)) {
                log.error("FTP 서버 연결 실패했습니다.");
            } else {
                if (!client.login(ftpId, ftpPassword)) {
                    log.error("FTP 로그인 실패했습니다.");
                    return;
                }
            }

            if (!client.changeWorkingDirectory(ftpBasePath)) {
                log.error("'" + ftpBasePath + "' 폴더가 없습니다.");
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public FTPUploader(Environment environment,
                       ImageProcessor imageProcessor,
                       @Value("${app.ftp.host}") String ftpHost,
                       @Value("${app.ftp.port}") String ftpPort,
                       @Value("${app.ftp.base-path}") String ftpBasePath,
                       @Value("${app.ftp.id}") String ftpId,
                       @Value("${app.ftp.password}") String ftpPassword) {
        this.environment = environment;
        this.ftpHost = ftpHost;
        this.ftpPort = ftpPort;
        this.ftpBasePath = ftpBasePath;
        this.ftpId = ftpId;
        this.ftpPassword = ftpPassword;
    }

    public void upload(TempFile tempImageFile) {
        try {
            client.storeFile(tempImageFile.getFile().getName(), Files.newInputStream(tempImageFile.getFile().toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String getFtpPath(String filePath) {
        return "https://" + ftpHost + "/" + ftpBasePath + "/" + FilenameUtils.getName(filePath);
    }
}
