package com.modoodesigner.config;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

@Configuration
public class FTPConfiguration {

    @Bean
    public SessionFactory<FTPFile> defaultFtpSessionFactory(@Value("${app.ftp.host}") String ftpHost,
                                                            @Value("${app.ftp.port}") String ftpPort,
                                                            @Value("${app.ftp.id}") String ftpId,
                                                            @Value("${app.ftp.password}") String ftpPassword) {
        DefaultFtpSessionFactory defaultFtpSessionFactory = new DefaultFtpSessionFactory();
        defaultFtpSessionFactory.setClientMode(FTPClient.PASSIVE_LOCAL_DATA_CONNECTION_MODE);
        defaultFtpSessionFactory.setHost(ftpHost);
        defaultFtpSessionFactory.setPort(NumberUtils.toInt(ftpPort));
        defaultFtpSessionFactory.setUsername(ftpId);
        defaultFtpSessionFactory.setPassword(ftpPassword);
        return new CachingSessionFactory<>(defaultFtpSessionFactory);
    }

}
