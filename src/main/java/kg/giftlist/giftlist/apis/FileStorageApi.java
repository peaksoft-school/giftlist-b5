//package kg.giftlist.giftlist.apis;
//
//import kg.giftlist.giftlist.config.s3.StorageService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.security.PermitAll;
//
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RestController
//@RequestMapping("api/files")
//@RequiredArgsConstructor
//@PermitAll
//public class FileStorageApi {
//
//    private StorageService amazonClient;
//
//    @Autowired
//    FileStorageApi(StorageService amazonClient) {
//        this.amazonClient = amazonClient;
//    }
//
//    @PostMapping("upload")
//    public String uploadFile(@RequestPart(value = "file") MultipartFile file) throws Exception {
//        return this.amazonClient.uploadFile(file);
//    }
//
//    @DeleteMapping("remove")
//    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
//        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
//    }
//}
