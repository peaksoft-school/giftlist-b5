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

@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
@CrossOrigin
@Tag(name = "AWS S3 API", description = "Any user can upload, download or delete files")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
@Tag(name = "Upload file to AWS", description = "Any user can upload, delete files from AWS")
public class FileStorageApi {

    private final StorageService s3service;

    @PostMapping(
            path = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Upload file", description = "User can upload file")
    Map<String, String> upload(@RequestPart(name = "file", required = false) MultipartFile file) throws IOException {
        return s3service.upload(file);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete file", description = "User can delete file")
    Map<String, String> delete(@RequestParam String fileLink) {
        return s3service.delete(fileLink);
    }
}
