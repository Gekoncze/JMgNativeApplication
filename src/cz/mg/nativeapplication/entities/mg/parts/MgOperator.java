package cz.mg.nativeapplication.entities.mg.parts;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;


public @Entity class MgOperator {
    public @Value Type type;
    public @Value String signs;

    public enum Type {
        LUNARY,
        BINARY,
        RUNARY
    }
}
