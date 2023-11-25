package com.timesheet.dto.mapper;

import com.manage.employeemanagementmodel.entity.Note;
import com.timesheet.dto.note.NoteFormDtoUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteFormDtoUserMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "note", source = "note")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "dateSubmit", source = "dateSubmit")
    @Mapping(target = "workingTime", source = "workingTime")
    @Mapping(target = "workingType", source = "workingType")
    @Mapping(target = "employee.id", source = "employeeId")
    @Mapping(target = "task.id", source = "taskId")
    Note noteFormDtoToNoteMapper(NoteFormDtoUser noteFormDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "note", source = "note")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "dateSubmit", source = "dateSubmit")
    @Mapping(target = "workingTime", source = "workingTime")
    @Mapping(target = "workingType", source = "workingType")
    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "taskId", source = "task.id")
    NoteFormDtoUser noteToNoteFormDtoMapper(Note note);
}
