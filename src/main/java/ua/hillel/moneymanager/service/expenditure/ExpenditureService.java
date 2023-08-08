package ua.hillel.moneymanager.service.expenditure;

import ua.hillel.moneymanager.dto.CategoryDto;
import ua.hillel.moneymanager.dto.ExpenditureDto;

import java.time.LocalDate;
import java.util.List;

public interface ExpenditureService {

    ExpenditureDto create(ExpenditureDto expenditureDto, String email);

    void delete(Long id, String categoryName, String email);

    ExpenditureDto update(Long id, String categoryName, ExpenditureDto expenditureDto, String email);

    List<ExpenditureDto> findAllByDate(String email, LocalDate date);
}
