package com.timesheet.service;

import com.manage.employeemanagementmodel.entity.Punishment;
import com.timesheet.dto.punishment.PunishmentDto;

import java.util.List;

public interface PunishmentService {

    List<PunishmentDto> getAll(Long employeeId, Integer month, Integer year, Boolean isDeleted);

    PunishmentDto updateComplain(Integer id, String complain);

    PunishmentDto updateComplainReply(Integer id, String complainReply);

    PunishmentDto updateIsDeleted(Integer id, Boolean isDeleted);
}
