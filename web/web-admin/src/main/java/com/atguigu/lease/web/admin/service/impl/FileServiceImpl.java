package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.common.minio.MinioProperties;
import com.atguigu.lease.web.admin.service.FileService;
import io.minio.*;

import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private MinioClient minioClient;

//    @Autowired
//    private MinioProperties properties;
    @Autowired
      private MinioProperties properties;


    @Override

    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(properties.getBucketName()).build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(properties.getBucketName()).build());
                minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(properties.getBucketName()).config(
                        """
                                {
                                  "Statement" : [ {
                                    "Action" : "s3:GetObject",
                                    "Effect" : "Allow",
                                    "Principal" : "*",
                                    "Resource" : "arn:aws:s3:::%s/*"
                                  } ],
                                  "Version" : "2012-10-17"
                                }
                                """.formatted(properties.getBucketName())
                ).build());
            }
            String filename = new SimpleDateFormat("yyyyMMdd")
                    .format(new Date()) + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
            minioClient.putObject(PutObjectArgs.builder().
                    bucket(properties.getBucketName())
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .object(filename)
                    //これを設定しないと、ファイルのタイプはstreamになる
                    .contentType(file.getContentType())
                    .build());
//            String url = properties.getEndpoint() + "/" + properties.getBucketName() + "/" + filename;
            String url = String.join("/", properties.getEndpoint(), properties.getBucketName(),filename);
            return url;

    }



}
