package cz.mg.nativeapplication.mg.entities.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;


public abstract @Entity class MgComponent {
    public @Value String name;
}
