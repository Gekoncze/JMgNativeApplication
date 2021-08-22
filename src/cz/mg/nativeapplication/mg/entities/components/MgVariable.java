package cz.mg.nativeapplication.mg.entities.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;


public @Entity class MgVariable extends MgComponent {
    public @Link MgType type;
    public @Value Integer pointers;
}
