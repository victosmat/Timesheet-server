package com.timesheet.service.impl;

import com.manage.employeemanagementmodel.entity.Employee;
import com.manage.employeemanagementmodel.entity.NoteComment;
import com.timesheet.dto.NoteComment.NoteCommentDto;
import com.timesheet.repository.EmployeeRepository;
import com.timesheet.repository.NoteCommentRepository;
import com.timesheet.repository.NoteRepository;
import com.timesheet.service.NoteCommentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NoteCommentServiceImpl implements NoteCommentService {
    private final NoteCommentRepository noteCommentRepository;
    private final NoteRepository noteRepository;
    private final EmployeeRepository employeeRepository;

    public NoteCommentServiceImpl(NoteCommentRepository noteCommentRepository, NoteRepository noteRepository, EmployeeRepository employeeRepository) {
        this.noteCommentRepository = noteCommentRepository;
        this.noteRepository = noteRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public NoteCommentDto save(NoteCommentDto noteCommentDto) {
        if (noteCommentDto.getId() == null) {
            NoteComment noteComment = new NoteComment();
            noteComment.setComment(noteCommentDto.getComment());
            Employee employee = new Employee();
            employee.setId(noteCommentDto.getEmployeeId());
            noteComment.setEmployee(employee);
            noteComment.setNote(noteRepository.findById(noteCommentDto.getNoteId()).orElse(null));
            noteComment.setReaded(false);
            noteCommentDto.setId(noteCommentRepository.save(noteComment).getId());
            return noteCommentDto;
        }
        NoteComment noteComment = noteCommentRepository.findById(noteCommentDto.getId()).orElse(null);
        if (noteComment != null) {
            noteComment.setComment(noteCommentDto.getComment());
            noteComment.setReaded(false);
            noteCommentRepository.save(noteComment);
            return noteCommentDto;
        }
        return null;
    }

    @Override
    public Boolean updateIsReaded(Integer noteCommentId) {
        NoteComment noteComment = noteCommentRepository.findById(noteCommentId).orElse(null);
        if (noteComment != null) {
            noteComment.setReaded(true);
            noteCommentRepository.save(noteComment);
            return true;
        }
        return false;
    }
}
