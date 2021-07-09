package cz.mg.nativeapplication.entities.mg.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;


public @Entity class MgInterface extends MgType {
    public @Part List<MgVariable> input;
    public @Part List<MgVariable> output;
}
