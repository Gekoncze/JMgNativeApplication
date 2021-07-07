package cz.mg.nativeapplication.entities.mg.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.entities.mg.parts.MgVariable;


public @Entity class MgDeclarationExpression extends MgExpression {
    public @Part MgVariable variable;
}
