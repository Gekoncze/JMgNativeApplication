package cz.mg.nativeapplication.entities.mg.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;


public @Entity class MgClass extends MgType {
    public @Link List<MgClass> classes = new List<>();
    public @Part List<MgVariable> variables = new List<>();
}
