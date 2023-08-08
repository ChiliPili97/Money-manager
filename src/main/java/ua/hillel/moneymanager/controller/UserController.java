package ua.hillel.moneymanager.controller;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ua.hillel.moneymanager.dto.UserDto;
import ua.hillel.moneymanager.repository.UserRepository;
import ua.hillel.moneymanager.service.user.UserService;

@RestController
@RequestMapping
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto) {
        return ResponseEntity
                .ok(userService.registerNewUser(userDto));
    }

    @GetMapping("/info")
    public ResponseEntity<UserDto> info(Authentication authentication) {
        return ResponseEntity
                .ok(userService.findByEmail(authentication.getName()));
    }
}
