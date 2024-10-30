package com.secure.notes.services;

import com.secure.notes.models.Note;

import java.util.List;

public interface NoteService {
    // Add CRUD methods for notes
    Note createNoteForUser(String username, String content);
    Note updateNoteForUser(Long noteId,String username, String content);
    void deleteNoteForUser(long noteId,String username);
    List<Note> getNotesForUser(String username);

}
