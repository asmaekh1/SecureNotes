package com.secure.notes.Controllers;

import com.secure.notes.models.Note;
import com.secure.notes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    //    @GetMapping("/hello")
//    String hello() {
//        return "Hello World";
//    }
//    @GetMapping("/public/contact")
//    String contact() {
//        return "Hello contact";
//    }  @GetMapping("/admin")
//    String admin() {
//        return "Hello admin";
//    }
    @Autowired
    private NoteService noteService;

    @PostMapping
    public Note createNote(@RequestBody String content, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        System.out.println("User Details: " + username);
        return noteService.createNoteForUser(username, content);
    }

    @GetMapping
    public List<Note> getUserNotes(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        System.out.println("User Details: " + username);
        return noteService.getNotesForUser(username);
    }

    @PutMapping("/{noteId}")
    public Note updateNote(@PathVariable Long noteId,
                           @RequestBody String content,
                           @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        noteService.updateNoteForUser(noteId, content, username);
        return noteService.updateNoteForUser(noteId, content, username);

    }

    @DeleteMapping("/{noteId}")
    public void deleteNote (@PathVariable Long noteId, @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        noteService.deleteNoteForUser(noteId, username);
        System.out.println("User Details: " + username);
    }
}
