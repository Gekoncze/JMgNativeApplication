package cz.mg.nativeapplication.entities.mg.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.entities.mg.components.MgVariable;


public @Entity class MgDeclarationExpression extends MgExpression {
    public @Part MgVariable variable;
}
