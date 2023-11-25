package com.timesheet.dto.mapper;

import com.manage.employeemanagementmodel.entity.Note;
import com.timesheet.dto.note.NoteFormDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteFormDtoMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "task.id", source = "taskId")
    @Mapping(target = "workingType", source = "workingType")
    @Mapping(target = "note", source = "noteDescription")
    @Mapping(target = "dateSubmit", source = "dateSubmit")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "workingTime", source = "workingTime")
    @Mapping(target = "employee.id", source = "employeeId")
    @Mapping(target = "dateModify", source = "dateModify")
    Note noteFormDtoToNote(NoteFormDto noteFormDto);
}
