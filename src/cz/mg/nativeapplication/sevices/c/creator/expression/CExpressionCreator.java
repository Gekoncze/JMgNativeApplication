package cz.mg.nativeapplication.sevices.c.creator.expression;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.mg.expression.*;


public @Service class CExpressionCreator {
    public String create(MgExpression mgExpression){
        if(mgExpression instanceof MgAssignmentOperatorExpression){
            return new CAssignmentOperatorExpressionCreator().create((MgAssignmentOperatorExpression) mgExpression);
        }

        if(mgExpression instanceof MgBinaryOperatorExpression){
            return new CBinaryOperatorExpressionCreator().create((MgBinaryOperatorExpression) mgExpression);
        }

        if(mgExpression instanceof MgDeclarationExpression){
            return new CDeclarationExpressionCreator().create((MgDeclarationExpression)mgExpression);
        }

        if(mgExpression instanceof MgDereferenceOperatorExpression){
            return new CDereferenceOperatorExpressionCreator().create((MgDereferenceOperatorExpression) mgExpression);
        }

        if(mgExpression instanceof MgFunctionExpression){
            return new CFunctionExpressionCreator().create((MgFunctionExpression) mgExpression);
        }

        if(mgExpression instanceof MgLunaryOperatorExpression){
            return new CLunaryOperatorExpressionCreator().create((MgLunaryOperatorExpression) mgExpression);
        }

        if(mgExpression instanceof MgMemberExpression){
            return new CMemberExpressionCreator().create((MgMemberExpression) mgExpression);
        }

        if(mgExpression instanceof MgReferenceOperatorExpression){
            return new CReferenceOperatorExpressionCreator().create((MgReferenceOperatorExpression) mgExpression);
        }

        if(mgExpression instanceof MgRunaryOperatorExpression){
            return new CRunaryOperatorExpressionCreator().create((MgRunaryOperatorExpression) mgExpression);
        }

        if(mgExpression instanceof MgValueExpression){
            return new CValueExpressionCreator().create((MgValueExpression) mgExpression);
        }

        if(mgExpression instanceof MgVariableExpression){
            return new CVariableExpressionCreator().create((MgVariableExpression) mgExpression);
        }

        throw new UnsupportedOperationException("Unsupported expression " + mgExpression.getClass().getSimpleName() + " in c expression creator.");
    }
}
