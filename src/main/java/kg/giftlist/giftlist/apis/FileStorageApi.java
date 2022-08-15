package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.config.s3.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "AWS S3 API", description = "Any user can upload, delete files")
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
@RequestMapping("/api/file")
public class FileStorageApi {

    private final StorageService s3service;

    @Operation(summary = "Upload file", description = "Any user can upload file")
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, String> upload(@RequestPart(name = "file", required = false) MultipartFile file) throws IOException {
        return s3service.upload(file);
    }

    @Operation(summary = "Delete file", description = "Any user can delete file")
    @DeleteMapping("/delete")
    Map<String, String> delete(@RequestParam String fileLink) {
        return s3service.delete(fileLink);
    }

}
