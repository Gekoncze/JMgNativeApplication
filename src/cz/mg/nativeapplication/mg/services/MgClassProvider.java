package cz.mg.nativeapplication.mg.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.collections.list.List;


/**
 * @see MgClassProviderGenerator
 **/
public @Service class MgClassProvider {
    public @Mandatory List<Class> get(){
        try {
            return new List<>(
                Class.forName("cz.mg.nativeapplication.mg.entities.MgProject"),
                Class.forName("cz.mg.nativeapplication.mg.entities.parts.MgValue"),
                Class.forName("cz.mg.nativeapplication.mg.entities.parts.MgOperatorType"),
                Class.forName("cz.mg.nativeapplication.mg.entities.parts.MgOperator"),
                Class.forName("cz.mg.nativeapplication.mg.entities.command.MgWhileCommand"),
                Class.forName("cz.mg.nativeapplication.mg.entities.command.MgReturnCommand"),
                Class.forName("cz.mg.nativeapplication.mg.entities.command.MgExpressionCommand"),
                Class.forName("cz.mg.nativeapplication.mg.entities.command.MgCaseCommand"),
                Class.forName("cz.mg.nativeapplication.mg.entities.command.MgSwitchCommand"),
                Class.forName("cz.mg.nativeapplication.mg.entities.command.MgContinueCommand"),
                Class.forName("cz.mg.nativeapplication.mg.entities.command.MgBreakCommand"),
                Class.forName("cz.mg.nativeapplication.mg.entities.components.MgStructure"),
                Class.forName("cz.mg.nativeapplication.mg.entities.components.MgAtom"),
                Class.forName("cz.mg.nativeapplication.mg.entities.components.MgInterface"),
                Class.forName("cz.mg.nativeapplication.mg.entities.components.MgFunction"),
                Class.forName("cz.mg.nativeapplication.mg.entities.components.MgLocation"),
                Class.forName("cz.mg.nativeapplication.mg.entities.components.MgVariable"),
                Class.forName("cz.mg.nativeapplication.mg.entities.expression.MgValueExpression"),
                Class.forName("cz.mg.nativeapplication.mg.entities.expression.MgDereferenceOperatorExpression"),
                Class.forName("cz.mg.nativeapplication.mg.entities.expression.MgRunaryOperatorExpression"),
                Class.forName("cz.mg.nativeapplication.mg.entities.expression.MgVariableExpression"),
                Class.forName("cz.mg.nativeapplication.mg.entities.expression.MgLunaryOperatorExpression"),
                Class.forName("cz.mg.nativeapplication.mg.entities.expression.MgBinaryOperatorExpression"),
                Class.forName("cz.mg.nativeapplication.mg.entities.expression.MgMemberExpression"),
                Class.forName("cz.mg.nativeapplication.mg.entities.expression.MgAssignmentOperatorExpression"),
                Class.forName("cz.mg.nativeapplication.mg.entities.expression.MgFunctionExpression"),
                Class.forName("cz.mg.nativeapplication.mg.entities.expression.MgReferenceOperatorExpression"),
                Class.forName("cz.mg.nativeapplication.mg.entities.expression.MgDeclarationExpression")
            );
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
