package ua.hillel.moneymanager.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ua.hillel.moneymanager.entity.Expenditure;
import ua.hillel.moneymanager.entity.User;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private List<ExpenditureDto> expenditures;
    private Long userId;
}
