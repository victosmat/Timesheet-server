package com.timesheet.dto.mapper;

import com.manage.employeemanagementmodel.entity.Note;
import com.timesheet.dto.note.NoteViewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteViewDtoMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "projectCode", source = "task.project.code")
    @Mapping(target = "taskName", source = "task.name")
    @Mapping(target = "noteDescription", source = "note")
    @Mapping(target = "workingTime", source = "workingTime")
    NoteViewDto noteToNoteViewDtoMapper(Note note);
}
