package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
    @Query("SELECT acc FROM Account acc WHERE acc.refreshToken.token = ?1")
    Account findByRefreshToken(String refreshToken);

    @Query("SELECT em.account.id FROM Employee em WHERE em.id = ?1")
    Integer getAccountByEmployeeId(Integer id);
}
