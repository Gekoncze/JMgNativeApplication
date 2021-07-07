package cz.mg.nativeapplication.entities.mg.parts;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.nativeapplication.entities.mg.components.MgType;


public @Entity class MgVariable {
    public @Link MgType type;
    public @Value Integer pointers;
    public @Value String name;
}
