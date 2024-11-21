package com.secure.notes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"), // ctd la valeur est unique
                @UniqueConstraint(columnNames = "email")
        })
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank // Valide que cet attribut ne soit ni null ni vide.
    @Size(max = 20)
    @Column(name = "username")
    private String userName;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = "email")
    private String email;

    @Size(max = 120)
    @Column(name = "password")
    @JsonIgnore //  Empêche l’inclusion du mot de passe lors de la sérialisation JSON (pour des raisons de sécurité).
    private String password;

    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    private LocalDate credentialsExpiryDate;
    private LocalDate accountExpiryDate;

    private String twoFactorSecret;
    private boolean isTwoFactorEnabled = false;
    private String signUpMethod;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    //@ManyToOne Chaque utilisateur est associé à un seul rôle
    // fetch = FetchType.EAGER : Charge immédiatement le rôle lors du chargement de l’utilisateur.
    // cascde merge : modifiez un objet Role associé à un User et que vous sauvegardez (merge) l'utilisateur,
    // la modification du rôle sera également automatiquement synchronisée dans la base de données.

    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    //role_id : est le nom de la colonne qui va etre creer dans la table users

    //referencedColumnName : (le nom de la colonne dans laquelle en pointe)
    @JsonBackReference
    @ToString.Exclude
    private Role role;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        return userId != null && userId.equals(((User) o).getUserId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


