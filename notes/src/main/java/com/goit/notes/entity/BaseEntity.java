package com.goit.notes.entity;

import java.io.Serializable;
import java.util.Collection;

public interface BaseEntity<ID> extends Serializable {

    Collection<? extends BaseEntity> getBaseEntity();
    ID getId();
    String getPassword();
    String getUsername();
    boolean isEnabled();
}
