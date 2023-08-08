package ua.hillel.moneymanager.mapper;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ua.hillel.moneymanager.dto.UserDto;
import ua.hillel.moneymanager.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper implements Mapper<User, UserDto> {

    private CategoryMapper categoryMapper;
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setLastName(user.getLastName());
        userDto.setFirstName(user.getFirstName());
        userDto.setCategories(categoryMapper.toDto(user.getCategories()));
        return userDto;
    }

    @Override
    public List<UserDto> toDto(List<User> users) {
        return users
                .stream()
                .map(entity -> toDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setLastName(user.getLastName());
        user.setFirstName(user.getFirstName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return user;
    }
}
