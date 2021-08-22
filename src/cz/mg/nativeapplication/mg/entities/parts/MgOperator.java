package cz.mg.nativeapplication.mg.entities.parts;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.nativeapplication.mg.Mg;


public @Mg @Entity class MgOperator {
    public @Value MgOperatorType type;
    public @Value String signs;
}
