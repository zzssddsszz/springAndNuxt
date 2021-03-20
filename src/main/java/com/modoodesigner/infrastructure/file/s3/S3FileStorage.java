package com.modoodesigner.infrastructure.file.s3;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.modoodesigner.domain.common.file.AbstractBaseFileStorage;
import com.modoodesigner.domain.common.file.FileStorageException;
import com.modoodesigner.domain.common.file.TempFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component("s3FileStorage")
@Slf4j
public class S3FileStorage extends AbstractBaseFileStorage {

    private Environment environment;
    private String rootTempPath;
    private AmazonS3 s3;

    public S3FileStorage(Environment environment,
                         @Value("${app.file-storage.temp-folder}") String rootTempPath) {
        this.environment = environment;
        this.rootTempPath = rootTempPath;
        if ("s3FileStorage".equals(environment.getProperty("app.file-storage.active"))) {
            this.s3 = initS3Client();
        }
    }

    @Override
    public TempFile saveAsTempFile(String folder, MultipartFile multipartFile) {
        return saveMultipartFileToLocalTempFolder(rootTempPath, folder, multipartFile);
    }

    @Override
    public void saveTempFile(TempFile tempFile) {
        Assert.notNull(s3, "S3FileStorage 가 초기화가 안 되었습니다.");

        String fileKey = tempFile.getFileRelativePath();
        String bucketName = environment.getProperty("app.file-storage.s3-bucket-name");
        Assert.hasText(bucketName, "프로퍼티 `app.file-storage.s3-bucket-name` 가 없습니다.");

        try {
            log.debug("Saving file '{}' to s3", tempFile.getFile().getName());
            PutObjectRequest putRequest = new PutObjectRequest(bucketName, fileKey, tempFile.getFile());
            putRequest.withCannedAcl(CannedAccessControlList.PublicRead);
            s3.putObject(putRequest);
            log.debug("파일 '{}' 가 s3 '{}' 에 저장되었습니다.", tempFile.getFile().getName(), fileKey);
        } catch (Exception e) {
            log.error("s3에 저장에 실패했습니다.", e);
            throw new FileStorageException("파일 '" + tempFile.getFile().getName() + "' 가 s3저장에 실패했습니다.", e);
        }
    }

    @Override
    public String saveUploaded(String folder, MultipartFile multipartFile) {
        Assert.notNull(s3, "S3FileStorage 가 초기화가 안 되었습니다.");

        String originalFileName = multipartFile.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());
        metadata.addUserMetadata("Original-File-Name", originalFileName);
        String finalFileName = generateFileName(multipartFile);
        String s3ObjectKey = folder + "/" + finalFileName;

        String bucketName = environment.getProperty("app.file-storage.s3-bucket-name");
        Assert.hasText(bucketName, "프로퍼티 `app.file-storage.s3-bucket-name` 가 없습니다.");

        try {
            log.debug("Saving file '{}' to s3", originalFileName);
            PutObjectRequest putRequest = new PutObjectRequest(
                    bucketName, s3ObjectKey, multipartFile.getInputStream(), metadata);
            putRequest.withCannedAcl(CannedAccessControlList.PublicRead);
            s3.putObject(putRequest);
            log.debug("파일 '{}' 가 s3 '{}' 에 저장되었습니다.", originalFileName, s3ObjectKey);
        } catch (IOException e) {
            log.error("s3에 파일저장에 실패했습니다.", e);
            throw new FileStorageException("s3에 파일 '" + multipartFile.getOriginalFilename() + "' 저장에 실패했습니다.");
        }

        return s3ObjectKey;
    }

    private AmazonS3 initS3Client() {
        String s3Region = environment.getProperty("app.file-storage.s3-region");
        Assert.hasText(s3Region, "`프로퍼티 `app.file-storage.s3-region` 가 없습니다.");

        List<String> profiles = Arrays.asList(environment.getActiveProfiles());
        List<String> localProfiles = Arrays.asList("local");
        if (!profiles.stream().filter(localProfiles::contains).findAny().isEmpty()) {
            log.debug("액세스 키 및 비밀 키로 dev S3 클라이언트 초기화");

            String s3AccessKey = environment.getProperty("app.file-storage.s3-access-key");
            String s3SecretKey = environment.getProperty("app.file-storage.s3-secret-key");

            Assert.hasText(s3AccessKey, "프로퍼티 `app.file-storage.s3-access-key` 가 없습니다.");
            Assert.hasText(s3SecretKey, "프로퍼티 `app.file-storage.s3-secret-key` 가 없습니다.");

            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(s3AccessKey, s3SecretKey);
            AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);

            AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard();
            builder.setRegion(s3Region);
            builder.withCredentials(credentialsProvider);
            return builder.build();
        } else {
            log.debug("IAM 역할을 사용하여 기본 S3 클라이언트 초기화");
            return AmazonS3ClientBuilder.standard()
                    .withCredentials(new InstanceProfileCredentialsProvider(false))
                    .withRegion(s3Region)
                    .build();
        }
    }
}
