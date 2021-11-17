package com.goit.notes.entity;

import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Data
@NoArgsConstructor
public class Note implements BaseEntity<UUID> {

    private static final long serialVersionUID = -748835835046696866L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
