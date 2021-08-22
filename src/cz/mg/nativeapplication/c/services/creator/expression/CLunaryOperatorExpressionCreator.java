package cz.mg.nativeapplication.c.services.creator.expression;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.mg.entities.expression.MgLunaryOperatorExpression;


public @Service class CLunaryOperatorExpressionCreator {
    public String create(MgLunaryOperatorExpression mgExpression){
        String operator = mgExpression.function.operator.signs;
        String right = new CExpressionCreator().create(mgExpression.right);
        return "(" + operator + " " + right + ")";
    }
}
