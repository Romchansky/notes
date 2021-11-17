package com.goit.notes.entity;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User implements BaseEntity<UUID> {

    private static final long serialVersionUID = 2868572978213680209L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator (name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type (type = "uuid-char")
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Note> notes;

}
