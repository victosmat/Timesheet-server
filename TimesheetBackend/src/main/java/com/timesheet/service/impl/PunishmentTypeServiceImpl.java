package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.PunishmentType;
import com.timesheet.dto.punishment.PunishmentTypeDto;
import com.timesheet.repository.PunishmentTypeRepository;
import com.timesheet.service.PunishmentTypeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PunishmentTypeServiceImpl implements PunishmentTypeService {
    private final PunishmentTypeRepository punishmentTypeRepository;

    public PunishmentTypeServiceImpl(PunishmentTypeRepository punishmentTypeRepository) {
        this.punishmentTypeRepository = punishmentTypeRepository;
    }

    @Override
    public List<PunishmentType> getAll() {
        return punishmentTypeRepository.findAll();
    }

    @Override
    public PunishmentType save(PunishmentTypeDto punishmentTypeDto) {
        if (punishmentTypeDto.getId() == null) {
            PunishmentType punishmentType = new PunishmentType();
            punishmentType.setName(punishmentTypeDto.getName());
            punishmentType.setDescription(punishmentTypeDto.getDescription());
            return punishmentTypeRepository.save(punishmentType);
        }
        PunishmentType punishmentType = punishmentTypeRepository.findById(punishmentTypeDto.getId()).orElse(null);
        if (punishmentType != null) {
            punishmentType.setName(punishmentTypeDto.getName());
            punishmentType.setDescription(punishmentTypeDto.getDescription());
            return punishmentTypeRepository.save(punishmentType);
        }
        return null;
    }
}
