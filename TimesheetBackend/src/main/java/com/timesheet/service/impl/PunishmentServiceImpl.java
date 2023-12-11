package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Punishment;
import com.manage.employeemanagementmodel.entity.enums.CheckInStatus;
import com.timesheet.dto.checkin.CheckinPunishmentResDto;
import com.timesheet.dto.mapper.PunishmentDtoMapper;
import com.timesheet.dto.punishment.PunishmentDto;
import com.timesheet.repository.PunishmentRepository;
import com.timesheet.service.PunishmentService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PunishmentServiceImpl implements PunishmentService {
    private final PunishmentRepository punishmentRepository;
    private final PunishmentDtoMapper punishmentDtoMapper;

    public PunishmentServiceImpl(PunishmentRepository punishmentRepository, PunishmentDtoMapper punishmentDtoMapper) {
        this.punishmentRepository = punishmentRepository;
        this.punishmentDtoMapper = punishmentDtoMapper;
    }

    @Override
    public List<PunishmentDto> getAll(Long employeeId, Integer month, Integer year, Boolean isDeleted) {
        return punishmentRepository.getAll(employeeId, month, year, isDeleted);
    }

    @Override
    public PunishmentDto updateComplain(Integer id, String complain) {
        Punishment punishment = punishmentRepository.findByCheckInId(id);
        if (punishment == null) return null;
        punishment.setComplain(complain);
        return punishmentDtoMapper.punishmentToPunishmentDtoMapper(punishmentRepository.save(punishment));
    }

    @Override
    public PunishmentDto updateComplainReply(Integer id, String complainReply) {
        Punishment punishment = punishmentRepository.findByCheckInId(id);
        if (punishment == null) return null;
        punishment.setComplainReply(complainReply);
        return punishmentDtoMapper.punishmentToPunishmentDtoMapper(punishmentRepository.save(punishment));
    }

    @Override
    public PunishmentDto updateIsDeleted(Integer id, Boolean isDeleted) {
        Punishment punishment = punishmentRepository.findByCheckInId(id);
        if (punishment != null) {
            punishment.setIsDeleted(isDeleted);
            return punishmentDtoMapper.punishmentToPunishmentDtoMapper(punishmentRepository.save(punishment));
        }
        return null;
    }
}
