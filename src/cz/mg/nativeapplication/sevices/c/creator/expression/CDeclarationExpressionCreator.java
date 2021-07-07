package cz.mg.nativeapplication.sevices.c.creator.expression;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.mg.expression.MgDeclarationExpression;
import cz.mg.nativeapplication.sevices.c.creator.CVariableCreator;
import cz.mg.nativeapplication.sevices.c.exporter.CVariableExporter;


public @Service class CDeclarationExpressionCreator {
    public String create(MgDeclarationExpression mgExpression){
        return new CVariableExporter().export(
            new CVariableCreator().create(
                mgExpression.variable
            )
        );
    }
}
