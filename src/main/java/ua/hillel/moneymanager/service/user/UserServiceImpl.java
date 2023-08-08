package ua.hillel.moneymanager.service.user;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.hillel.moneymanager.dto.UserDto;
import ua.hillel.moneymanager.mapper.UserMapper;
import ua.hillel.moneymanager.repository.UserRepository;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        var user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto findByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email));
    }
}
