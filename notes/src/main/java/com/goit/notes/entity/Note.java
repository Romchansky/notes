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
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "note")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Note implements BaseEntity<UUID> {

    private static final long serialVersionUID = -748835835046696866L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator (name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type (type = "uuid-char")
    @Column(name = "id")
    private UUID id;

    @NotBlank
    @Size(min = 5, max = 100, message = "Name length must be 5-100 symbols")
    @Column(name = "name", length = 100)
    private String name;
    
    @NotBlank
    @Size(min = 5, max = 10000, message = "Text length must be 5-10000 symbols")
    @Column(name = "description", length = 10000)
    private String description;

    @Column(name = "access", length = 10)
    @Enumerated(EnumType.STRING)
    private Access access;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;

        Note note = (Note) o;

        if (!id.equals(note.id)) return false;
        if (!name.equals(note.name)) return false;
        if (!description.equals(note.description)) return false;
        if (access != note.access) return false;
        return user.equals(note.user);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + access.hashCode();
        result = 31 * result + user.hashCode();
        return result;
    }
}
