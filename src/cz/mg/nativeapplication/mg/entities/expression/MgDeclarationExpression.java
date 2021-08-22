package cz.mg.nativeapplication.mg.entities.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.mg.Mg;
import cz.mg.nativeapplication.mg.entities.components.MgVariable;


public @Mg @Entity class MgDeclarationExpression extends MgExpression {
    public @Part MgVariable variable;
}
