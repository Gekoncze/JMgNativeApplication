package cz.mg.nativeapplication.mg.entities.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;


public @Entity class MgLocation extends MgComponent {
    public @Part List<MgComponent> components = new List<>();
    public @Part MgComponent component;
}
