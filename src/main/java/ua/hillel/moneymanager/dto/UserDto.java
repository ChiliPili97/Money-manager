package ua.hillel.moneymanager.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ua.hillel.moneymanager.entity.Category;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<CategoryDto> categories;
}
