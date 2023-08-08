package ua.hillel.moneymanager.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class ExpenditureDto {
    private Long id;
    private Double amount;
    private String description;
    private LocalDateTime createdAt;
    private Long categoryId;
}
