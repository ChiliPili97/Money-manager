package ua.hillel.moneymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.hillel.moneymanager.dto.ExpenditureDto;
import ua.hillel.moneymanager.entity.Category;
import ua.hillel.moneymanager.entity.Expenditure;
import ua.hillel.moneymanager.service.expenditure.ExpenditureService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {

    void deleteById(Long id);

   @Query("select e from Expenditure e where date(e.createdAt) = :localDate and e.category.user.id = :userId")
    List<Expenditure> findByCreatedAt(Long userId, LocalDate localDate);
}
