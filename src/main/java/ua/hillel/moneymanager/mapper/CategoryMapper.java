package ua.hillel.moneymanager.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hillel.moneymanager.dto.CategoryDto;
import ua.hillel.moneymanager.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CategoryMapper implements Mapper<Category, CategoryDto> {

    private ExpenditureMapper expenditureMapper;
    @Override
    public CategoryDto toDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setId(category.getId());
        categoryDto.setUserId(category.getUser().getId());
        categoryDto.setExpenditures(expenditureMapper.toDto(category.getExpenditures()));
        return categoryDto;
    }

    @Override
    public List<CategoryDto> toDto(List<Category> categories) {
        if (categories == null) {
            return null;
        }

        return categories
                .stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return category;
    }
}
