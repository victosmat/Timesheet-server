package com.timesheet.service;

import com.timesheet.dto.NoteComment.NoteCommentDto;

public interface NoteCommentService {
    NoteCommentDto save(NoteCommentDto noteCommentDto);

    Boolean updateIsReaded(Integer noteCommentId);
}
