package com.secure.notes.models;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // used to generate unique  identifier for the note
    @Column(name = "")
    private String ownerUsername;
    @Lob // Lob is marks an attribute as a large object, which means the attribute will be stored in a database as either BLOB (Binary Large Object)
    @Column(name = "")
    private String content;



}
