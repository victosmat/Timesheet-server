package com.timesheet.dto.mapper;

import com.manage.employeemanagementmodel.entity.Punishment;
import com.timesheet.dto.punishment.PunishmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PunishmentDtoMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "complain", source = "complain")
    @Mapping(target = "complainReply", source = "complainReply")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "punishmentTypeName", source = "punishmentType.name")
    @Mapping(target = "employeeId", source = "checkIn.employee.id")
    @Mapping(target = "isDeleted", source = "isDeleted")
    PunishmentDto punishmentToPunishmentDtoMapper(Punishment punishment);
}
