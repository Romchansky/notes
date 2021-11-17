package com.goit.notes.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usr")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User implements BaseEntity<UUID, Note> {

    private static final long serialVersionUID = 2868572978213680209L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(min = 5, max = 50, message = "Name length must be 5-50 symbols")
    @Column(name = "user_name", unique = true, length = 50)
    private String userName;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 100, message = "Password  must be 8-100 symbols")
    @Column(name = "password", length = 100)
    private String password;
    private boolean active;

    @Column(name = "user_role", length = 10)
    @Enumerated(EnumType.STRING)
    private Role userRole;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "usr", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Override
//    public int hashCode() {
//        int result = id.hashCode();
//        result = 31 * result + userName.hashCode();
//        result = 31 * result + password.hashCode();
//        result = 31 * result + userRole.hashCode();
//        result = 31 * result + notes.hashCode();
//        return result;
//    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
    @Override
    public boolean isActive() {
        return active;
    }
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public Object getAccess() {
        return null;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    @Override
    public void setUsername(String userName) {
        this.userName = userName;
    }

}