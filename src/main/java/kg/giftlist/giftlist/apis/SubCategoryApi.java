package kg.giftlist.giftlist.apis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.SubCategoryService;
import kg.giftlist.giftlist.dto.subCategories.SubCategoriesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
@CrossOrigin
@RequestMapping("api/subCategories")
@Tag(name = "SubCategory API", description = "Any can get all subCategories")
public class SubCategoryApi {

    private final SubCategoryService subCategoryService;

    @Operation(summary = "SubCategory", description = "get all subCategories by Category Id")
    @GetMapping("{categoryId}")
    public List<SubCategoriesResponse> getAllSubCategoriesByCategoryId(@PathVariable Long categoryId) {
        return subCategoryService.getAllSubCategories(categoryId);
    }
}
