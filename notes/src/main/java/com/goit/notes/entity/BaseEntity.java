package com.goit.notes.entity;

import java.io.Serializable;

public interface BaseEntity<ID, N> extends Serializable {

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
