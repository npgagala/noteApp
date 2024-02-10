package com.exam.notes.controller;

import com.exam.notes.entity.Note;
import com.exam.notes.repository.NoteRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@RestController
public class NoteController {
    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/notes")
    List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @PostMapping("/notes")
    Note saveNote(@RequestBody @Valid Note note) {
        return noteRepository.save(note);
    }

    @GetMapping("/notes/{id}")
    Note getNoteById(@PathVariable("id") Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Note with id " + id + " does not exist."));
    }

    @PutMapping("/notes/{id}")
    Note updateNote(@RequestBody @Valid Note newNote, @PathVariable Long id) {
        return noteRepository.findById(id)
                .map(note -> {
                    note.setTitle(newNote.getTitle());
                    note.setBody(newNote.getBody());
                    return noteRepository.save(note);
                })
                .orElseThrow(() -> new NoSuchElementException("Note with id " + id + " does not exist."));
    }

    @DeleteMapping("notes/{id}")
    void deleteNote(@PathVariable("id") Long id) {
        noteRepository.deleteById(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponse notFound(NoSuchElementException ex) {
        return ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> invalidInput(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
