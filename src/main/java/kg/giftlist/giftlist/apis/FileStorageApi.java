package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.config.s3.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;

@RestController
@RequestMapping("api/files")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "AWS S3 API", description = "Any user can upload, download or delete files")
public class FileStorageApi {

    private final StorageService storageService;

    @Operation(summary = "Upload file", description = "Uploading file to the application")
    @PostMapping("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam(value = "files")
                                             @RequestPart MultipartFile file) {


        return new ResponseEntity<>(storageService.uploadFile(file), HttpStatus.OK);

    }



    @Operation(summary = "Download file", description = "Downloading file from application")
    @GetMapping("download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {
        byte[] data = storageService.downloadFile(fileName);

        return ( ResponseEntity
                .ok().header("Content-Disposition","attachment; filename=" + fileName))
                .body(data);
    }

    @Operation(summary = "Delete file", description = "Deleting file from application")
    @DeleteMapping("remove/{fileName}")
    public ResponseEntity<?> deleteFile(@PathVariable String fileName) {
        storageService.deleteFile(fileName);
        return new ResponseEntity<>("", HttpStatus.OK);
    }


}
