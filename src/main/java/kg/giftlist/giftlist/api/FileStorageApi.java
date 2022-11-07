package kg.giftlist.giftlist.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/files")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
@Tag(name = "Storage API", description = "Any user can upload, delete files")
public class FileStorageApi {

    private final StorageService s3service;

    @Operation(summary = "Upload file", description = "Any user can upload file")
    @PostMapping(path = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    Map<String, String> upload(@RequestPart(name = "file", required = false) MultipartFile file) throws IOException {
        return s3service.upload(file);
    }

    @Operation(summary = "Delete file", description = "Any user can delete file")
    @DeleteMapping()
    Map<String, String> delete(@RequestParam String fileLink) {
        return s3service.delete(fileLink);
    }

}
