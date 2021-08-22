package cz.mg.nativeapplication.c.services.creator.expression;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.mg.entities.expression.MgDeclarationExpression;
import cz.mg.nativeapplication.c.services.creator.CVariableCreator;
import cz.mg.nativeapplication.c.services.exporter.CVariableExporter;


public @Service class CDeclarationExpressionCreator {
    public String create(MgDeclarationExpression mgExpression){
        return new CVariableExporter().export(
            new CVariableCreator().create(
                mgExpression.variable
            )
        );
    }
}
