package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.PunishmentType;
import com.timesheet.dto.punishment.PunishmentTypeDto;

import java.util.List;

public interface PunishmentTypeService {
    List<PunishmentType> getAll();

    PunishmentType save(PunishmentTypeDto punishmentTypeDto);
}
