package kg.giftlist.giftlist.config.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@Service
@Slf4j
@RequiredArgsConstructor
public class StorageService {

    @Value("${s3.bucketName}")
    private String bucketName;
    @Value("${s3.bucketUrl}")
    private String bucketUrl;
    private final AmazonS3 s3Client;

    public String uploadFile(MultipartFile file) {

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.addUserMetadata("Content-Type", file.getContentType());
        metadata.addUserMetadata("Content-Length", String.valueOf(file.getSize()));
        try {
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata));
        } catch (IOException e) {
            return "cannot not file exception";
        }
        return bucketUrl + fileName;
    }

    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteFile(String fileName) {
        try {
            s3Client.deleteObject(bucketName, fileName);{
                log.info("successfully delete");
            }
        } catch (AmazonServiceException ex) {
            log.info("failed to delete file = {} from amazon s3 bucket", fileName);
            throw new AmazonServiceException(
                    String.format("failed to delete file [%s] from amazon", fileName)
            );
        }
    }
}