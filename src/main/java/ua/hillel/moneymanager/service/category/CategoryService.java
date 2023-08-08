package ua.hillel.moneymanager.service.category;

import org.springframework.data.domain.Page;
import ua.hillel.moneymanager.dto.CategoryDto;

public interface CategoryService {

    CategoryDto create(CategoryDto categoryDto, String email);

    Page<CategoryDto> findAllByUserId(String email, int pageNumber, int pageSize);

    void delete(String name, String email);

    CategoryDto update(String name, CategoryDto categoryDto, String email);

    CategoryDto findByName(String name, String email);
}
