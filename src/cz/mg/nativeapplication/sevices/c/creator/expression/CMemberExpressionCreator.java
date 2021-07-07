package cz.mg.nativeapplication.sevices.c.creator.expression;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.mg.expression.MgMemberExpression;
import cz.mg.nativeapplication.sevices.mg.resolver.MgExpressionOutputResolver;


public @Service class CMemberExpressionCreator {
    public String create(MgMemberExpression mgExpression){
        String parent = new CExpressionCreator().create(mgExpression.parent);
        int pointers = new MgExpressionOutputResolver().resolve(mgExpression.parent).pointers;
        if(pointers < 0 || pointers > 1) throw new IllegalStateException("Illegal pointer count for member access.");
        String operator = pointers == 0 ? "." : "->";
        String child = mgExpression.child.name;
        return "(" + parent + ")" + operator + child;
    }
}
