package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Punishment;
import com.timesheet.dto.punishment.PunishmentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PunishmentRepository extends JpaRepository<Punishment, Integer> {

    @Query(value = "SELECT new com.timesheet.dto.punishment.PunishmentDto(p.id, p.complain, p.complainReply, p.date, p.punishmentType.name, p.checkIn.employee.id, p.isDeleted) " +
            "FROM Punishment p " +
            "WHERE (:employeeId IS NULL OR p.checkIn.employee.id = :employeeId) " +
            "AND (:month IS NULL OR MONTH(p.date) = :month) " +
            "AND (:year IS NULL OR YEAR(p.date) = :year) " +
            "AND (:isDeleted IS NULL OR p.isDeleted = :isDeleted) " +
            "ORDER BY p.date DESC")
    List<PunishmentDto> getAll(Long employeeId, Integer month, Integer year, Boolean isDeleted);

    @Query("SELECT p FROM Punishment p WHERE p.checkIn.id = :id")
    Punishment findByCheckInId(Integer id);
}