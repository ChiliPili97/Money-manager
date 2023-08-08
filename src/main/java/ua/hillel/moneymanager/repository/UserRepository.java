package ua.hillel.moneymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hillel.moneymanager.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String username);
}
