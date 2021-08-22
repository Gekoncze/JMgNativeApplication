package cz.mg.nativeapplication.mg.entities.expression;

import cz.mg.annotations.classes.Entity;
import cz.mg.annotations.storage.Part;
import cz.mg.nativeapplication.mg.entities.components.MgVariable;


public @Entity class MgDeclarationExpression extends MgExpression {
    public @Part MgVariable variable;
}
