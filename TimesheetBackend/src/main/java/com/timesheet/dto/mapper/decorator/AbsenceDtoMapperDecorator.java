package com.timesheet.dto.mapper.decorator;

import com.manage.employeemanagementmodel.entity.Absence;
import com.timesheet.dto.absence.AbsenceDto;
import com.timesheet.dto.mapper.AbsenceDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbsenceDtoMapperDecorator implements AbsenceDtoMapper {
    @Autowired
    @Qualifier("delegate")
    private AbsenceDtoMapper delegate;

    @Override
    public Absence absenceDtoToAbsence(AbsenceDto absenceDto) {
        return delegate.absenceDtoToAbsence(absenceDto);
    }
}
