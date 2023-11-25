package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.PunishmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PunishmentTypeRepository extends JpaRepository<PunishmentType, Integer> {
    @Query("SELECT p FROM PunishmentType p WHERE p.name = ?1")
    PunishmentType customFindByName(String name);
}