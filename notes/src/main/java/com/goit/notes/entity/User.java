package com.goit.notes.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User implements BaseEntity<UUID> {

    private static final long serialVersionUID = 2868572978213680209L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @NotBlank
    @Size(min = 5, max = 50, message = "Name length must be 5-50 symbols")
    @Column(name = "user_name", unique = true, length = 50)
    private String userName;

    @NotBlank
    @Size(min = 8, max = 100, message = "Password  must be 8-100 symbols")
    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "user_role", length = 10)
    @Enumerated(EnumType.STRING)
    private Role userRole;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Note> notes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!userName.equals(user.userName)) return false;
        if (!password.equals(user.password)) return false;
        if (userRole != user.userRole) return false;
        return notes.equals(user.notes);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + userRole.hashCode();
        result = 31 * result + notes.hashCode();
        return result;
    }
}