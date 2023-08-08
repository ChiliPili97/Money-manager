package ua.hillel.moneymanager.service.expenditure;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hillel.moneymanager.dto.CategoryDto;
import ua.hillel.moneymanager.dto.ExpenditureDto;
import ua.hillel.moneymanager.entity.Expenditure;
import ua.hillel.moneymanager.mapper.ExpenditureMapper;
import ua.hillel.moneymanager.repository.CategoryRepository;
import ua.hillel.moneymanager.repository.ExpenditureRepository;
import ua.hillel.moneymanager.repository.UserRepository;
import ua.hillel.moneymanager.service.category.CategoryService;
import ua.hillel.moneymanager.service.category.CategoryServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class ExpenditureServiceImpl implements ExpenditureService {

    private ExpenditureRepository expenditureRepository;
    private ExpenditureMapper expenditureMapper;
    private CategoryServiceImpl categoryService;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    @Override
    @Transactional
    public ExpenditureDto create(ExpenditureDto expenditureDto, String email) {
        Objects.requireNonNull(expenditureDto.getAmount(), "Expenditure.amount must be provided");
        Objects.requireNonNull(expenditureDto.getCategoryId(), "Expenditure.category must be provided");

        Expenditure expenditure = expenditureMapper.toEntity(expenditureDto);
        expenditure = expenditureRepository.save(expenditure);
        return expenditureMapper.toDto(expenditure);
    }

    @Override
    @Transactional
    public void delete(Long id, String categoryName, String email) {
        categoryService.isPresentForCurrentUser(categoryName, email);
        expenditureRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ExpenditureDto update(Long id, String categoryName, ExpenditureDto expenditureDto, String email) {
        categoryService.isPresentForCurrentUser(categoryName, email);
        Expenditure expenditure = expenditureRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Expenditure with id: %d not found".formatted(id)));

        if (expenditureDto.getCategoryId() != null) {
            Long newCategoryId = expenditureDto.getCategoryId();
            expenditure.setCategory(categoryRepository
                    .findById(newCategoryId)
                    .orElseThrow(() -> new RuntimeException("Expenditure with id: %d not found".formatted(newCategoryId))));
        } else if (expenditureDto.getDescription() != null) {
            expenditure.setDescription(expenditureDto.getDescription());
        } else if (expenditureDto.getAmount() != null) {
            expenditure.setAmount(expenditureDto.getAmount());
        }
        return expenditureMapper.toDto(expenditureRepository.save(expenditure));
    }

    @Override
    @Transactional
    public List<ExpenditureDto> findAllByDate(String email, LocalDate date) {
        var userId = userRepository.findByEmail(email).getId();
        var expenditures = expenditureRepository.findByCreatedAt(userId, date) ;
        return expenditureMapper.toDto(expenditures);
    }
}
