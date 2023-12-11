package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.CheckIn;
import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.Punishment;
import com.manage.employeemanagementmodel.entity.enums.CheckInStatus;
import com.manage.employeemanagementmodel.entity.enums.PunishmentType;
import com.manage.employeemanagementmodel.entity.enums.TypeTimeOff;
import com.timesheet.dto.CheckInDto;
import com.timesheet.dto.absence.AbsenceDto;
import com.timesheet.dto.checkin.CheckinPunishmentDto;
import com.timesheet.dto.checkin.CheckinPunishmentResDto;
import com.timesheet.dto.checkin.ICheckInManageDto;
import com.timesheet.dto.request_body.CheckInRequestDto;
import com.timesheet.repository.*;
import com.timesheet.service.CheckInService;
import com.timesheet.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CheckInServiceImpl implements CheckInService {

    private final CheckInRepository checkInRepository;
    private final AbsenceRepository absenceRepository;
    private final PunishmentTypeRepository punishmentTypeRepository;
    private final EmployeeService employeeService;
    private final PunishmentRepository punishmentRepository;
    private final EmployeeRepository employeeRepository;

    public CheckInServiceImpl(CheckInRepository checkInRepository, AbsenceRepository absenceRepository, PunishmentTypeRepository punishmentTypeRepository, EmployeeService employeeService, PunishmentRepository punishmentRepository, EmployeeRepository employeeRepository) {
        this.checkInRepository = checkInRepository;
        this.absenceRepository = absenceRepository;
        this.punishmentTypeRepository = punishmentTypeRepository;
        this.employeeService = employeeService;
        this.punishmentRepository = punishmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<CheckInDto> getAllCheckInBySpecificMonthAndYear(CheckInRequestDto request) {
        return checkInRepository.getAllCheckInBySpecificMonthAndYear(request);
    }

    @Override
    public boolean saveCheckPointInDay(Integer employeeId, LocalDateTime checkPointTime) {
        try {
            CheckInDto checkInDto = checkInRepository.getCheckInByLocalDateTime(checkPointTime, employeeId);
            CheckIn checkIn = checkInRepository.findById(checkInDto.getId()).orElse(null);
            Employee employee = new Employee();
            employee.setId(employeeId);
            assert checkIn != null;
            checkIn.setEmployee(employee);
            if (checkInDto.getCheckInTime() == null) {
                checkIn.setCheckInTime(checkPointTime);
                checkIn.setCheckOutTime(null);
                checkIn.setStatus(CheckInStatus.CHECK_POINT);
            } else {
                checkIn.setId(checkInDto.getId());
                checkIn.setCheckInTime(checkInDto.getCheckInTime());
                checkIn.setCheckOutTime(checkPointTime);
                checkIn.setStatus(checkInDto.getStatus());
            }
            checkInRepository.save(checkIn);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Integer> getAllEmployeeNotAbsenceInDay(LocalDate localDate) {
        return absenceRepository.getAllEmployeeNotAbsenceInDay(localDate);
    }

    @Override
    @Async
    public void checkPunishmentCheckin(Integer employeeId, LocalDate localDate) {
        List<AbsenceDto> absenceDtoList = absenceRepository.getAbsenceInCurrentDay(employeeId, localDate);
        CheckInDto checkInDto = checkInRepository.getCheckInOfEmployeePerDay(employeeId, localDate);
        LocalDateTime checkIn = checkInDto.getCheckInTime();
        LocalDateTime checkOut = checkInDto.getCheckOutTime();
        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        Punishment punishment = new Punishment();
        CheckIn checkInEntity = new CheckIn();
        checkInEntity.setId(checkInDto.getId());
        punishment.setCheckIn(checkInEntity);
        punishment.setDate(LocalDate.now());
        punishment.setComplain(null);
        punishment.setComplainReply(null);
        if (absenceDtoList.isEmpty()) {
            if (checkIn != null && checkOut != null) {
                checkPunishmentOfFullyCheckinDay(checkIn, checkOut, punishment);
            }
            if (checkIn == null && checkOut == null) {
                punishment.setIsDeleted(false);
                punishment.setPunishmentType(punishmentTypeRepository.customFindByName(com.manage.employeemanagementmodel.entity.enums.PunishmentType.NOT_CHECK_IN_AND_OUT.toString()));
                punishmentRepository.save(punishment);
            }
            if (checkOut == null && checkIn != null) {
                Duration checkInDuration = Duration.between(checkIn.toLocalTime(), startTime);
                checkPunishmentWhenNotCheckIn(punishment, checkInDuration);
            }
        } else {
            if (checkIn != null && checkOut != null) {
                for (AbsenceDto absence : absenceDtoList) {
                    switch (absence.getTypeTimeOff()) {
                        case MORNING, COME_LATE:
                            startTime = startTime.plusMinutes((long) absence.getTimeOff().doubleValue() * 60);
                            break;
                        case AFTERNOON, WENT_SOON:
                            endTime = endTime.minusMinutes((long) absence.getTimeOff().doubleValue() * 60);
                            break;
                        case FULL_DAY:
                            return;
                    }
                }
                checkPunishmentOfFullyCheckinDay(checkIn, checkOut, punishment);
            }
            if (checkIn == null && checkOut == null) {
                for (AbsenceDto absence : absenceDtoList) {
                    if (absence.getTypeTimeOff() == TypeTimeOff.FULL_DAY) return;
                }
                punishment.setIsDeleted(false);
                punishment.setPunishmentType(punishmentTypeRepository.customFindByName(com.manage.employeemanagementmodel.entity.enums.PunishmentType.NOT_CHECK_IN_AND_OUT.toString()));
                punishmentRepository.save(punishment);
            }
            if (checkOut == null && checkIn != null) {
                Duration checkInDuration = Duration.between(checkIn.toLocalTime(), startTime);
                for (AbsenceDto absence : absenceDtoList) {
                    startTime = switch (absence.getTypeTimeOff()) {
                        case MORNING, COME_LATE ->
                                startTime.plusMinutes((long) absence.getTimeOff().doubleValue() * 60);
                        default -> startTime;
                    };
                }
                checkPunishmentWhenNotCheckIn(punishment, checkInDuration);
            }
        }
    }

    @Override
    public CheckIn findById(Integer id) {
        return checkInRepository.findById(id).orElse(null);
    }

    @Override
    public Object updateStatus(Integer id, String status) {
        CheckIn checkIn = checkInRepository.findById(id).orElse(null);
        if (checkIn == null) return null;
        checkIn.setStatus(CheckInStatus.valueOf(status));
        return checkInRepository.save(checkIn);
    }

    @Override
    public Page<CheckinPunishmentResDto> getCheckinOfEmployeeAndPunishmentByStatus(
            Integer employeeId, CheckInStatus status, int month, int year,
            Boolean isComplain, Pageable pageable, boolean isManage) {

        Page<CheckinPunishmentDto> checkinPunishmentDtoPage =
                checkInRepository.getCheckinOfEmployeeAndPunishmentByStatus(employeeId, status, month, year, pageable);

        List<CheckinPunishmentDto> checkinPunishmentDtoList = checkinPunishmentDtoPage.getContent();

        if (isComplain != null)
            checkinPunishmentDtoList = checkinPunishmentDtoList.stream()
                    .filter(checkinPunishmentDto -> isComplain.equals(checkinPunishmentDto.getComplain() != null)).toList();

        if (isManage)
            checkinPunishmentDtoList = checkinPunishmentDtoList.stream()
                    .filter(checkinPunishmentDto -> checkinPunishmentDto.getPunishmentTypeDes() != null).toList();

        List<CheckinPunishmentResDto> checkinPunishmentResDtoList =
                addCheckinPunishmentResDtos(checkinPunishmentDtoList);

        return new PageImpl<>(checkinPunishmentResDtoList, pageable, checkinPunishmentDtoPage.getTotalElements());
    }


    @Override
    public Page<ICheckInManageDto> getAllCheckinAndPunishment(String keyword, Integer month, Integer year, String department, Pageable pageable) {
        return checkInRepository.getAllCheckinAndPunishment(keyword, month, year, department, pageable);
    }

    private List<CheckinPunishmentResDto> addCheckinPunishmentResDtos(List<CheckinPunishmentDto> checkinPunishmentDtoList) {
        List<CheckinPunishmentResDto> checkinPunishmentResDtos = new ArrayList<>();
        checkinPunishmentDtoList.forEach(checkinPunishmentDto -> {
            checkinPunishmentResDtos.add(new CheckinPunishmentResDto(
                    checkinPunishmentDto.getId(),
                    checkinPunishmentDto.getCurrentDate() == null ? null : checkinPunishmentDto.getCurrentDate().toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    checkinPunishmentDto.getTimeCheckin() == null ? null : checkinPunishmentDto.getTimeCheckin().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                    checkinPunishmentDto.getTimeCheckout() == null ? null : checkinPunishmentDto.getTimeCheckout().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                    checkinPunishmentDto.getStatus() == null ? null : checkinPunishmentDto.getStatus(),
                    checkinPunishmentDto.getPunishmentTypeDes() == null ? null : checkinPunishmentDto.getPunishmentTypeDes(),
                    checkinPunishmentDto.getPunishmentMoney() == null ? null : checkinPunishmentDto.getPunishmentMoney(),
                    checkinPunishmentDto.getComplain() == null ? null : checkinPunishmentDto.getComplain(),
                    checkinPunishmentDto.getComplainReply() == null ? null : checkinPunishmentDto.getComplainReply(),
                    checkinPunishmentDto.getDeleted() == null ? null : checkinPunishmentDto.getDeleted()));
        });
        return checkinPunishmentResDtos;
    }

    @Override
    public List<CheckinPunishmentResDto> getCheckinOfEmployeeAndPunishment(Integer employeeId, int month, int year) {
        List<CheckinPunishmentDto> checkinPunishmentDtoList = checkInRepository.getCheckinOfEmployeeAndPunishment(employeeId, month, year);
        return addCheckinPunishmentResDtos(checkinPunishmentDtoList);
    }

    @Override
    public void saveInitDate(Integer id, LocalDateTime localDateTime) {
        checkInRepository.save(new CheckIn(null, localDateTime, null, null, null, employeeRepository.findById(id).orElse(null)));
    }

    @Override
    public int checkCurrentDateIsExist(LocalDateTime localDateTime) {
        return checkInRepository.checkCurrentDateIsExist(localDateTime);
    }

    private void checkPunishmentWhenNotCheckIn(Punishment punishment, Duration checkInDuration) {
        String punishmentName;
        if (!checkInDuration.isNegative()) {
            punishmentName = com.manage.employeemanagementmodel.entity.enums.PunishmentType.CHECK_IN_LATE.toString();
        } else {
            punishmentName = com.manage.employeemanagementmodel.entity.enums.PunishmentType.CHECK_IN_LATE_AND_NOT_CHECK_OUT.toString();
        }
        punishment.setIsDeleted(false);
        punishment.setPunishmentType(punishmentTypeRepository.customFindByName(punishmentName));
        punishmentRepository.save(punishment);
    }

    private void checkPunishmentOfFullyCheckinDay(LocalDateTime checkIn, LocalDateTime checkOut, Punishment punishment) {
        LocalTime startOfWork = LocalTime.of(8, 0);
        LocalTime endOfWork = LocalTime.of(17, 0);

        boolean isLateCheckIn = checkIn.toLocalTime().isAfter(startOfWork);
        boolean isLateCheckOut = checkOut != null && checkOut.toLocalTime().isBefore(endOfWork);

        if (isLateCheckIn && isLateCheckOut) {
            punishment.setIsDeleted(false);
            punishment.setPunishmentType(punishmentTypeRepository.customFindByName(PunishmentType.CHECK_IN_LATE_AND_NOT_CHECK_OUT.toString()));
            punishmentRepository.save(punishment);
        } else if (isLateCheckIn) {
            punishment.setIsDeleted(false);
            punishment.setPunishmentType(punishmentTypeRepository.customFindByName(PunishmentType.CHECK_IN_LATE.toString()));
            punishmentRepository.save(punishment);
        } else if (isLateCheckOut) {
            punishment.setIsDeleted(false);
            punishment.setPunishmentType(punishmentTypeRepository.customFindByName(PunishmentType.NOT_CHECK_OUT.toString()));
            punishmentRepository.save(punishment);
        }
    }

}
