package ua.hillel.moneymanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ua.hillel.moneymanager.dto.CategoryDto;
import ua.hillel.moneymanager.entity.Category;
import ua.hillel.moneymanager.service.category.CategoryServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto, Authentication authentication) {
        return ResponseEntity
                .ok(this.categoryService.create(categoryDto, authentication.getName()));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CategoryDto>> findAllByUserId(Authentication authentication,
                                                             @RequestParam(defaultValue = "0") int pageNumber,
                                                             @RequestParam(defaultValue = "5") int pageSize) {
        return ResponseEntity
                .ok(this.categoryService.findAllByUserId(authentication.getName(), pageNumber, pageSize));
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<CategoryDto> findByName(@PathVariable String categoryName, Authentication authentication) {
        return ResponseEntity
                .ok(categoryService.findByName(categoryName, authentication.getName()));
    }

    @DeleteMapping("/delete/{categoryName}")
    public void delete(@PathVariable String categoryName, Authentication authentication) {
        categoryService.delete(categoryName, authentication.getName());
    }

    @PutMapping("/update/{categoryName}")
    public ResponseEntity<CategoryDto> update(@PathVariable String categoryName, @RequestBody CategoryDto categoryDto, Authentication authentication) {
        return ResponseEntity
                .ok(this.categoryService.update(categoryName, categoryDto, authentication.getName()));
    }
}
