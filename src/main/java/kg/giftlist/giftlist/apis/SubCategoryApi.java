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
<<<<<<< HEAD
@RequestMapping("api/subCategories")
@Tag(name = "SubCategory API", description = "Any users can get all subCategories")
=======
@CrossOrigin
@RequestMapping("api/subCategories")
@Tag(name = "SubCategory API", description = "Any user can get all subCategories")
>>>>>>> 31c5df82a735a4fee7dab7c3a4baaf98fe203696
public class SubCategoryApi {

    private final SubCategoryService subCategoryService;

<<<<<<< HEAD
    @Operation(summary = "Get all subCategories", description = "Get all subCategories by Category Id")
=======
    @Operation(summary = "Get subCategory", description = "Get all subCategories by Category Id")
>>>>>>> 31c5df82a735a4fee7dab7c3a4baaf98fe203696
    @GetMapping("{categoryId}")
    public List<SubCategoriesResponse> getAllSubCategoriesByCategoryId(@PathVariable Long categoryId) {
        return subCategoryService.getAllSubCategories(categoryId);
    }
}
