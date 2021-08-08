package cz.mg.nativeapplication.entities.qt;


import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Value;


public @Entity class QtConfig {
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
