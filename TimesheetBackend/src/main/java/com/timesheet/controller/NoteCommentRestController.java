package com.timesheet.controller;

import com.timesheet.dto.NoteComment.NoteCommentDto;
import com.timesheet.service.NoteCommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/note_comments")
@SecurityRequirement(name = "bearer-key")
public class NoteCommentRestController {
    private final NoteCommentService noteCommentService;

    public NoteCommentRestController(NoteCommentService noteCommentService) {
        this.noteCommentService = noteCommentService;
    }

    @PostMapping("/save")
    public ResponseEntity<NoteCommentDto> save(@RequestBody NoteCommentDto noteCommentDto) {
        return ResponseEntity.ok(noteCommentService.save(noteCommentDto));
    }

    @GetMapping("/update_isReaded")
    public ResponseEntity<Boolean> updateIsReaded(@RequestParam("noteCommentId") Integer noteCommentId) {
        return ResponseEntity.ok(noteCommentService.updateIsReaded(noteCommentId));
    }
}
