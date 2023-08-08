package ua.hillel.moneymanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ua.hillel.moneymanager.dto.CategoryDto;
import ua.hillel.moneymanager.dto.ExpenditureDto;
import ua.hillel.moneymanager.entity.Category;
import ua.hillel.moneymanager.repository.CategoryRepository;
import ua.hillel.moneymanager.repository.ExpenditureRepository;
import ua.hillel.moneymanager.service.expenditure.ExpenditureService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class ExpenditureController {

    private final ExpenditureService expenditureService;

    @PostMapping("/{categoryName}/expenditures/create")
    public ResponseEntity<ExpenditureDto> create(@PathVariable String categoryName,
                                                 @RequestBody ExpenditureDto expenditureDto,
                                                 Authentication authentication) {
        return ResponseEntity
                .ok(this.expenditureService.create(expenditureDto, authentication.getName()));
    }

    @DeleteMapping("{categoryName}/expenditures/{id}/delete")
    public ResponseEntity<String> delete(@PathVariable String categoryName,
                                         @PathVariable Long id,
                                         Authentication authentication) {
        expenditureService.delete(id, categoryName, authentication.getName());
        return ResponseEntity.ok("Expenditure with id: %d has been deleted".formatted(id));
    }

    @PutMapping("{categoryName}/expenditures/{id}/update")
    public ResponseEntity<ExpenditureDto> updateCategory(@PathVariable String categoryName,
                                                         @PathVariable Long id,
                                                         @RequestBody ExpenditureDto expenditureDto,
                                                         Authentication authentication) {
        return ResponseEntity
                .ok(this.expenditureService.update(id, categoryName, expenditureDto, authentication.getName()));
    }

    @GetMapping("/date")
    public ResponseEntity<List<ExpenditureDto>> findByCreateDate(Authentication authentication,
                                                             @RequestParam(required = true) int year,
                                                             @RequestParam(required = true) int month,
                                                             @RequestParam(required = true) int day) {
        return ResponseEntity
                .ok(this.expenditureService.findAllByDate(authentication.getName(), LocalDate.of(year, month, day)));
    }
}
