package kg.giftlist.giftlist.config.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Getter
@Setter
@Configuration
public class StorageConfig {

    @Value("${s3.access-key}")
    private String AWS_ACCESS_KEY_ID;

    @Value("${s3.secret-key}")
    private String AWS_SECRET_ACCESS_KEY;

    @Value("${s3.region}")
    private String REGION;

    @Bean
    S3Client s3Client() {
        Region region = Region.of(REGION);
        final AwsBasicCredentials credentials = AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY);
        return S3Client.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

}