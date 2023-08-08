package ua.hillel.moneymanager.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.hillel.moneymanager.dto.ExpenditureDto;
import ua.hillel.moneymanager.entity.Expenditure;
import ua.hillel.moneymanager.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ExpenditureMapper implements Mapper<Expenditure, ExpenditureDto> {

    private CategoryRepository categoryRepository;

    @Override
    public ExpenditureDto toDto(Expenditure expenditure) {
        ExpenditureDto expenditureDto = new ExpenditureDto();
        expenditureDto.setId(expenditure.getId());
        expenditureDto.setCategoryId(expenditure.getCategory().getId());
        expenditureDto.setAmount(expenditure.getAmount());
        expenditureDto.setDescription(expenditure.getDescription());
        expenditureDto.setCreatedAt(expenditure.getCreatedAt());
        return expenditureDto;
    }

    @Override
    public List<ExpenditureDto> toDto(List<Expenditure> expenditures) {
        if (expenditures == null) {
            return null;
        }

        return expenditures
                .stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public Expenditure toEntity(ExpenditureDto expenditureDto) {
        Expenditure expenditure = new Expenditure();
        expenditure.setAmount(expenditureDto.getAmount());
        expenditure.setCategory(categoryRepository.findById(expenditureDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category with id: %d not found".formatted(expenditureDto.getCategoryId()))));
        expenditure.setDescription(expenditureDto.getDescription());
        expenditure.setCreatedAt(LocalDateTime.now());
        return expenditure;
    }
}
