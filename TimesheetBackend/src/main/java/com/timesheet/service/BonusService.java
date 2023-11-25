package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Bonus;
import com.timesheet.dto.bonus.BonusDto;

import java.util.List;

public interface BonusService {
    List<Bonus> getAll(String keyword);

    Bonus save(BonusDto bonusDto);

    Bonus getById(Integer id);

    void delete(Integer id);

}