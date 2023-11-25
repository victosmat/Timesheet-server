package com.timesheet.repository;

import com.manage.employeemanagementmodel.entity.NoteComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteCommentRepository extends JpaRepository<NoteComment, Integer> {
}
