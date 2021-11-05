package cz.mg.nativeapplication.mg.entities.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.annotations.Name;
import cz.mg.nativeapplication.mg.entities.annotations.Required;


public abstract @Mg @Entity class MgComponent {
    public @Required @Name @Value String name;
}
