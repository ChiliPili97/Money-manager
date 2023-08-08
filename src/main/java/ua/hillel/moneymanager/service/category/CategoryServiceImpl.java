package ua.hillel.moneymanager.service.category;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ua.hillel.moneymanager.dto.CategoryDto;
import ua.hillel.moneymanager.entity.Category;
import ua.hillel.moneymanager.mapper.CategoryMapper;
import ua.hillel.moneymanager.repository.CategoryRepository;
import ua.hillel.moneymanager.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    private UserRepository userRepository;

    @Override
    @Transactional
    public CategoryDto create(CategoryDto categoryDto, String email) {
        Objects.requireNonNull(categoryDto.getName(), "Category.name must be provided");
        Category category = categoryMapper.toEntity(categoryDto);
        category.setUser(userRepository.findByEmail(email));
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    @Transactional()
    public Page<CategoryDto> findAllByUserId(String email, int pageNumber, int pageSize) {
        Long userId = userRepository.findByEmail(email).getId();
        var categoriesDto = categoryMapper.toDto(categoryRepository.findAllByUserId(userId));
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);
        Page<CategoryDto> categoryPage = new PageImpl<>(categoriesDto, pageable, categoriesDto.size());

        return categoryPage;
    }

    @Override
    @Transactional
    public void delete(String name, String email) {
        categoryRepository.deleteById(isPresentForCurrentUser(name, email));
    }

    @Override
    public CategoryDto update(String name, CategoryDto categoryDto, String email) {
        Objects.requireNonNull(categoryDto.getName());
        var categoryId = isPresentForCurrentUser(name, email);
        var category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category with id: %d not found".formatted(categoryId)));
        category.setName(categoryDto.getName());
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto findByName(String name, String email) {
        var categoryId = isPresentForCurrentUser(name, email);
        return categoryMapper
                .toDto(categoryRepository
                        .findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Category with id: %d not found".formatted(categoryId))));
    }

    public Long isPresentForCurrentUser(String categoryName, String userEmail) {
        System.out.println(userEmail);
        List<Category> categories = categoryRepository.findAllByUserId(userRepository.findByEmail(userEmail).getId());
        var category = categories
                .stream()
                .filter(c -> c.getName().equals(categoryName))
                .findFirst();
        category.orElseThrow(() -> new RuntimeException("Category with name: %s not found".formatted(categoryName)));
        return category.get().getId();
    }
}
