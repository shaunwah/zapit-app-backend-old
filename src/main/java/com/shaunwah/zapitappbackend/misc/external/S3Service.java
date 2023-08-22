package com.shaunwah.zapitappbackend.misc.external;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.shaunwah.zapitappbackend.misc.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log
public class S3Service {
    private final AmazonS3 s3;

    @Value("${cloudflare.r2.bucket.name}")
    private String bucketName;

    public List<Bucket> listBuckets() {
        return s3.listBuckets();
    }

    public List<S3ObjectSummary> listObjects() {
        return s3.listObjectsV2(bucketName).getObjectSummaries();
    }

    public String uploadObject(File file) {
        String objectKey = "%s.%s".formatted(UUID.randomUUID().toString(), Utilities.getFileExtension(file));
        try {
            s3.putObject(bucketName, objectKey, file);
            return objectKey;
        } catch (AmazonServiceException e) {
            log.severe(e.getErrorMessage());
            return null;
        }
    }
}
