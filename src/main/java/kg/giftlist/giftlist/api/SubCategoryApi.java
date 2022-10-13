package kg.giftlist.giftlist.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.SubCategoryService;
import kg.giftlist.giftlist.dto.subCategories.SubCategoriesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/subCategories")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
@Tag(name = "SubCategory API", description = "Any users can get all subCategories")
public class SubCategoryApi {

    private final SubCategoryService subCategoryService;

    @Operation(summary = "Get all subCategories", description = "Get all subCategories by Category Id")
    @GetMapping("{categoryId}")
    public List<SubCategoriesResponse> getAllSubCategoriesByCategoryId(@PathVariable Long categoryId) {
        return subCategoryService.getAllSubCategories(categoryId);
    }

}
