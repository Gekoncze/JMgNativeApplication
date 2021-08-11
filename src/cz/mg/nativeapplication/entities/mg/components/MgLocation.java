package cz.mg.nativeapplication.entities.mg.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;


public @Entity class MgLocation extends MgComponent {
    public @Part List<MgComponent> components = new List<>();
    public @Part MgComponent component;
}
