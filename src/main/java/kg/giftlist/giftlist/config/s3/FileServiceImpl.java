package kg.giftlist.giftlist.config.s3;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileServiceImpl {
    String saveFile(MultipartFile file);

    byte[] downloadFile(String filename);


    String deleteFile(String filename);


    List<String> listAllFiles();
}
