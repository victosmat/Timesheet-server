package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BonusRepository extends JpaRepository<Bonus, Integer> {
    @Query("SELECT b FROM Bonus b WHERE b.name LIKE %?1%"
            + " OR b.description LIKE %?1%")
    List<Bonus> findAllByKeyword(String keyword);
}
