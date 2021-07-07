package cz.mg.nativeapplication.sevices.c.creator.expression;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.mg.expression.MgAssignmentOperatorExpression;


public @Service class CAssignmentOperatorExpressionCreator {
    public String create(MgAssignmentOperatorExpression mgExpression){
        String left = new CExpressionCreator().create(mgExpression.left);
        String operator = "=";
        String right = new CExpressionCreator().create(mgExpression.right);
        return "(" + left + " " + operator + " " + right + ")";
    }
}
