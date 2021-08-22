package cz.mg.nativeapplication.c.services.creator.expression;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.mg.entities.expression.MgDereferenceOperatorExpression;


public @Service class CDereferenceOperatorExpressionCreator {
    public String create(MgDereferenceOperatorExpression mgExpression){
        String operator = "*";
        String right = new CExpressionCreator().create(mgExpression.right);
        return "(" + operator + " " + right + ")";
    }
}
