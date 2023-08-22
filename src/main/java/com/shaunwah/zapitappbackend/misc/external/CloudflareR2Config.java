package com.shaunwah.zapitappbackend.misc.external;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudflareR2Config {
    @Value("${cloudflare.r2.api.url}")
    private String apiUrl;

    @Value("${cloudflare.r2.api.key.access}")
    private String apiKeyAccess;

    @Value("${cloudflare.r2.api.key.secret}")
    private String apiKeySecret;

    @Value("${cloudflare.r2.api.region:auto}")
    private String apiRegion;

    @Bean
    public AmazonS3 getS3() {
        AWSCredentials credentials = new BasicAWSCredentials(
                apiKeyAccess,
                apiKeySecret
        );

        return AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(apiUrl, apiRegion))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}
