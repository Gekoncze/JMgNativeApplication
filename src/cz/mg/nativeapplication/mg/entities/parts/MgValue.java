package cz.mg.nativeapplication.mg.entities.parts;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.components.MgType;


public @Mg @Entity class MgValue {
    public @Link MgType type;
    public @Value String value;
}
