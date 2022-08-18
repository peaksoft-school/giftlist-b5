package kg.giftlist.giftlist.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kg.giftlist.giftlist.db.service.CategoryService;
import kg.giftlist.giftlist.dto.categories.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
@RequiredArgsConstructor
@Tag(name = "Category API", description = "Any can get all categories")
public class CategoryApi {
    private final CategoryService categoryService;

    @Operation(summary = "Category", description = "get all categories")
    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.findAllCategories();
    }
}
