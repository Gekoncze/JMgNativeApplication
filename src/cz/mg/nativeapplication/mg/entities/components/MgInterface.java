package cz.mg.nativeapplication.mg.entities.components;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.mg.Mg;


public @Mg @Entity class MgInterface extends MgType {
    public @Part List<MgVariable> input;
    public @Part List<MgVariable> output;
}
