package cz.mg.nativeapplication.mg.entities.parts;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Value;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.annotations.Required;


public @Mg @Entity class MgOperator {
    public @Required @Value MgOperatorType type;
    public @Required @Value String signs;
}
