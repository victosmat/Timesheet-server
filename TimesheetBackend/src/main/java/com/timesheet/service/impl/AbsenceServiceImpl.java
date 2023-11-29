package com.timesheet.service.impl;

import ch.qos.logback.classic.Logger;
import com.manage.employeemanagementmodel.entity.Absence;
import com.manage.employeemanagementmodel.entity.enums.AbsenceStatus;
import com.manage.employeemanagementmodel.entity.enums.TypeTimeOff;
import com.manage.employeemanagementmodel.exception.AbsenceRequestNotFoundException;
import com.timesheet.controller.EmployeeRestController;
import com.timesheet.dto.absence.AbsenceDto;
import com.timesheet.dto.absence.AbsenceManageViewDto;
import com.timesheet.dto.absence.AbsenceViewDto;
import com.timesheet.dto.mapper.AbsenceDtoMapper;
import com.timesheet.repository.AbsenceRepository;
import com.timesheet.repository.NoteRepository;
import com.timesheet.service.AbsenceService;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AbsenceServiceImpl implements AbsenceService {

    private final AbsenceRepository absenceRepository;
    private final AbsenceDtoMapper absenceDtoMapper;

    public AbsenceServiceImpl(AbsenceRepository absenceRepository, AbsenceDtoMapper absenceDtoMapper,
                              NoteRepository noteRepository) {
        this.absenceRepository = absenceRepository;
        this.absenceDtoMapper = absenceDtoMapper;
    }

    @Override
    public Map<AbsenceStatus, List<AbsenceDto>> listAllAbsenceRequestInMonthAndYearWithAbsenceType(Integer month, Integer year, Integer absenceTypeId) {
        List<AbsenceDto> absenceDtoList = absenceRepository.listAllAbsenceRequestInMonthAndYearWithAbsenceType(month, year, absenceTypeId);
        return absenceDtoList.stream().collect(Collectors.groupingBy(AbsenceDto::getStatus, Collectors.toList()));
    }

    @Override
    public void saveAbsenceRequest(AbsenceDto absenceDto) {
        Absence absenceRequest = absenceDtoMapper.absenceDtoToAbsence(absenceDto);
        System.out.println(absenceRequest);
        switch (absenceRequest.getTypeTimeOff()) {
            case MORNING:
                absenceRequest.setTimeOff(3.5);
                break;
            case AFTERNOON:
                absenceRequest.setTimeOff(4.5);
                break;
            case FULL_DAY:
                absenceRequest.setTimeOff(8.0);
                break;
            case COME_LATE:
            case WENT_SOON:
                absenceRequest.setTimeOff(absenceDto.getTimeOff());
                break;
            default:
                break;
        }
        absenceRequest.setStatus(AbsenceStatus.PENDING);
        absenceRequest.setDateRequest(absenceRequest.getDateRequest().plusDays(1));
        absenceRequest.setPunishmentStatus(false);
        absenceRepository.save(absenceRequest);
    }

    @Override
    public boolean deletePendingAbsenceRequest(Integer absenceRequestId) throws AbsenceRequestNotFoundException {
        Optional<Absence> absence = absenceRepository.findById(absenceRequestId);
        if (absence.isPresent()) {
            if (absence.get().getStatus() == AbsenceStatus.PENDING) {
                try {
                    absenceRepository.deleteById(absenceRequestId);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            } else {
                return false;
            }
        } else {
            throw new AbsenceRequestNotFoundException("Cannot find any Absence Request with the given information");
        }
    }

    @Override
    public AbsenceDto getAbsenceByDate(LocalDate date) {
        return absenceRepository.getByDate(date);
    }

    @Override
    public void updateAbsenceStatus(Integer absenceId, AbsenceStatus status) {
        absenceRepository.updateAbsenceStatus(absenceId, status);
    }

    @Override
    public Page<AbsenceDto> listAllAbsenceRequestInMonthAndYearOfEmployee(Integer month, Integer year, Integer employeeId, Pageable pageable) {
        return absenceRepository.listAllAbsenceRequestInMonthAndYearOfEmployee(month, year, employeeId, pageable);
    }

    @Override
    public List<LocalDate> ListAllDayAbsenceInParticularMonthAndYearOfEmployee(Integer month, Integer year, Integer employeeId) {
        return absenceRepository.ListAllDayAbsenceInParticularMonthAndYearOfEmployee(month, year, employeeId);
    }

    @Override
    public List<AbsenceViewDto> getAbsenceByDateAndEmployee(LocalDate date, Integer employeeId) {
        return absenceRepository.getAbsenceByDateAndEmployee(date, employeeId);
    }

    @Override
    public AbsenceDto findFormById(Integer id) {
        return absenceRepository.findFormById(id);
    }

    @Override
    public Map<AbsenceStatus, List<AbsenceViewDto>> listAllAbsenceOfStaffInParticularMonthAndYear(int staffId, int month, int year) {
        List<AbsenceViewDto> absenceDtoList = absenceRepository.listAllAbsenceOfStaffInParticularMonth(staffId, month, year);
        return absenceDtoList.stream().collect(Collectors.groupingBy(AbsenceViewDto::getStatus, Collectors.toList()));
    }

    @Override
    public void updatePendingAbsenceStatus(int absenceId, AbsenceStatus status) {
        absenceRepository.updatePendingAbsenceStatus(absenceId, status);
    }

    @Override
    public List<AbsenceDto> listAllAbsenceRequestInMonthAndYear(Integer monthNumber, Integer yearNumber, String email, AbsenceStatus absenceStatus, TypeTimeOff typeTimeOff) {
        return absenceRepository.listAllAbsenceRequestInMonthAndYear(monthNumber, yearNumber, email, absenceStatus, typeTimeOff);
    }

    @Override
    public List<LocalDate> ListAllDayAbsenceInParticularMonthAndYear(Integer month, Integer year, String email, AbsenceStatus absenceStatus, TypeTimeOff typeTimeOff) {
        return absenceRepository.ListAllDayAbsenceInParticularMonthAndYear(month, year, email, absenceStatus, typeTimeOff);
    }

    @Override
    public List<AbsenceManageViewDto> getAbsenceByDateAllEmployee(LocalDate localDate, String email, AbsenceStatus absenceStatus, TypeTimeOff typeTimeOff) {
        return absenceRepository.getAbsenceByDateAllEmployee(localDate, email, absenceStatus, typeTimeOff);
    }

}
