package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    @Query("SELECT r FROM RefreshToken r WHERE r.token = ?1")
    RefreshToken findByToken(String refreshToken);
}
