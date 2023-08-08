package ua.hillel.moneymanager.service.user;

import ua.hillel.moneymanager.dto.UserDto;

public interface UserService {

    UserDto registerNewUser(UserDto userDto);

    UserDto findByEmail(String email);
}
