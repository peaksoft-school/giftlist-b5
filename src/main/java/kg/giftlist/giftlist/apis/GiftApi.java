package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.CategoryService;
import kg.giftlist.giftlist.db.service.SubCategoryService;
import kg.giftlist.giftlist.db.service.impl.GiftServiceImpl;
import kg.giftlist.giftlist.dto.SimpleResponse;
import kg.giftlist.giftlist.dto.categories.CategoryResponse;
import kg.giftlist.giftlist.dto.gift.GiftRequest;
import kg.giftlist.giftlist.dto.gift.GiftResponse;
import kg.giftlist.giftlist.dto.subCategories.SubCategoriesResponse;
import kg.giftlist.giftlist.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/gifts")
@CrossOrigin
@PreAuthorize("hasAnyAuthority('USER')")
@Tag(name = "Gift API", description = "User with role \"User\"  can create, update or delete gifts")
public class GiftApi {

    private final GiftServiceImpl giftService;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    @Operation(summary = "Create gift", description = "The user can create a gift")
    @PostMapping
    public GiftResponse create(@RequestBody GiftRequest giftRequest) {
        return giftService.create(giftRequest);
    }

    @Operation(summary = "Update gift", description = "The user can update a gift ")
    @PutMapping("/{giftId}")
    public GiftResponse update(@PathVariable Long giftId, @RequestBody GiftRequest request) {
        return giftService.update(giftId, request);
    }

    @Operation(summary = "Delete gift", description = " User can delete by gift id")
    @DeleteMapping("/{giftId}")
    public SimpleResponse deleteById(@PathVariable Long giftId) {
        return giftService.deleteById(giftId);
    }

    @Operation(summary = "Find gift by id", description = "The user can find gift")
    @GetMapping("/{giftId}")
    public GiftResponse findById(@PathVariable Long giftId) {
        return giftService.getGiftById(giftId);
    }

    @Operation(summary = "Get all gifts", description = "User can get all gifts")
    @GetMapping
    public List<GiftResponse> getAllGifts() {
        return giftService.getAll();
    }

    @Operation(summary = "Get all categories", description = "User can get all categories")
    @GetMapping("/findAll/categories")
    public List<CategoryResponse> getAllCategories() {
        return categoryService.findAllCategories();
    }

    @Operation(summary = "SubCategory",description = "User can get all subCategories")
    @GetMapping("/{categoryId}")
    public List<SubCategoriesResponse> getAllSubCategoriesByCategoryId(@PathVariable Long categoryId){
        return subCategoryService.getAllSubCategories(categoryId);
    }

}
