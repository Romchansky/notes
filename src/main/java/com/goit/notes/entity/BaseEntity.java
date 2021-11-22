package com.goit.notes.entity;

import java.io.Serializable;

@FunctionalInterface
public interface BaseEntity<ID> extends Serializable {

    ID getId();
}
