package cz.mg.nativeapplication.c.services.creator.expression;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.mg.entities.expression.MgReferenceOperatorExpression;


public @Service class CReferenceOperatorExpressionCreator {
    public String create(MgReferenceOperatorExpression mgExpression){
        String operator = "&";
        String right = new CExpressionCreator().create(mgExpression.right);
        return "(" + operator + " " + right + ")";
    }
}
