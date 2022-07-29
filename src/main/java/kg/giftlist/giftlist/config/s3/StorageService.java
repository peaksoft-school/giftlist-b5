package kg.giftlist.giftlist.config.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class StorageService {

    @Value("${s3.bucketName}")
    private String bucketName;
    private final AmazonS3 s3Client;

    public String uploadFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);
        String fileName =new Random().nextInt(100000000)+""+System.currentTimeMillis() + fileObj.hashCode() + "." + getFileExtension(fileObj);
        fileName = fileName.replace('-', ' ');
        fileName = fileName.replaceAll("\\s+", "");
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        fileObj.delete();
        return fileName;
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
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
        s3Client.deleteObject(bucketName,fileName);
        try {
            s3Client.deleteObject(bucketName, fileName);
        } catch (AmazonServiceException ex) {
            log.info("failed to delete file = {} from amazon s3 bucket", fileName);
            throw new AmazonServiceException(
                    String.format("failed to delete file [%s] from amazon", fileName)
            );
        }

    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file ", e);
        }
        return convertedFile;
    }
}