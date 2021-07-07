package cz.mg.nativeapplication.entities.mg.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;


public abstract @Entity class MgComponent {
    public @Value String name;
}
