package cz.mg.nativeapplication.qt.entities;


import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.EntityClass;


public @Entity class QtConfig {
    public static EntityClass entity;

    public @Value String name;
    public @Value Operation operation;
    public @Value String value;

    public QtConfig() {
    }

    public QtConfig(@Mandatory String name, @Mandatory Operation operation, @Mandatory String value) {
        this.name = name;
        this.operation = operation;
        this.value = value;
    }

    public enum Operation {
        ADD,
        REMOVE
    }
}
