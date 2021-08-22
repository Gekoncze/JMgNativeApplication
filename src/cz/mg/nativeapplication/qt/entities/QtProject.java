package cz.mg.nativeapplication.qt.entities;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;


public @Entity class QtProject {
    public @Value String name;
    public @Mandatory @Part List<QtConfig> settings = new List<>();
}
