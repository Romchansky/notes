package com.goit.notes.entity;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.Serializable;

public interface BaseEntity<ID, N> extends Serializable {

    User loadUserByUsername(String userName) throws UsernameNotFoundException;

    boolean isAdmin();
    Long getId();
    String getPassword();
    void setPassword(String password);
    String getUsername();
    boolean isEnabled();

    boolean isActive();

    void setActive(boolean active);

    Object getAccess();

    void setUsername(String userName);
}
