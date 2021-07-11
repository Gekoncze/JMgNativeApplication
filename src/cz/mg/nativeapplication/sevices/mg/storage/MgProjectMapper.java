package cz.mg.nativeapplication.sevices.mg.storage;

import cz.mg.annotations.classes.Utility;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.mg.*;
import cz.mg.nativeapplication.entities.mg.command.*;
import cz.mg.nativeapplication.entities.mg.components.*;
import cz.mg.nativeapplication.entities.mg.existing.MgExistingFunction;
import cz.mg.nativeapplication.entities.mg.existing.MgExistingInterface;
import cz.mg.nativeapplication.entities.mg.existing.MgExistingLocation;
import cz.mg.nativeapplication.entities.mg.existing.MgExistingStructure;
import cz.mg.nativeapplication.entities.mg.expression.*;
import cz.mg.nativeapplication.entities.mg.parts.MgOperator;
import cz.mg.nativeapplication.entities.mg.parts.MgValue;
import cz.mg.nativeapplication.entities.mg.components.MgVariable;
import cz.mg.nativeapplication.mapper.EntityObjectMapper;
import cz.mg.nativeapplication.mapper.EnumObjectMapper;
import cz.mg.nativeapplication.mapper.Mapper;
import cz.mg.nativeapplication.mapper.collection.ListObjectMapper;
import cz.mg.nativeapplication.mapper.value.BooleanObjectMapper;
import cz.mg.nativeapplication.mapper.value.IntegerObjectMapper;
import cz.mg.nativeapplication.mapper.value.StringObjectMapper;


public @Utility class MgProjectMapper extends Mapper<MgProject> {
    private static MgProjectMapper instance = null;

    public static MgProjectMapper getInstance(){
        if(instance == null) instance = new MgProjectMapper();
        return instance;
    }

    private MgProjectMapper() {
        super(new List<>(
            new IntegerObjectMapper(),
            new BooleanObjectMapper(),
            new StringObjectMapper(),
            new ListObjectMapper(),
            // ----------
            new EntityObjectMapper(MgAtom.class),
            new EntityObjectMapper(MgFunction.class),
            new EntityObjectMapper(MgInterface.class),
            new EntityObjectMapper(MgLocation.class),
            new EntityObjectMapper(MgOperator.class),
            new EnumObjectMapper(MgOperator.Type.class),
            new EntityObjectMapper(MgProject.class),
            new EntityObjectMapper(MgStructure.class),
            new EntityObjectMapper(MgValue.class),
            new EntityObjectMapper(MgVariable.class),
            // ----------
            new EntityObjectMapper(MgBreakCommand.class),
            new EntityObjectMapper(MgCaseCommand.class),
            new EntityObjectMapper(MgContinueCommand.class),
            new EntityObjectMapper(MgExpressionCommand.class),
            new EntityObjectMapper(MgReturnCommand.class),
            new EntityObjectMapper(MgSwitchCommand.class),
            new EntityObjectMapper(MgWhileCommand.class),
            // ----------
            new EntityObjectMapper(MgExistingFunction.class),
            new EntityObjectMapper(MgExistingInterface.class),
            new EntityObjectMapper(MgExistingLocation.class),
            new EntityObjectMapper(MgExistingStructure.class),
            // ----------
            new EntityObjectMapper(MgAssignmentOperatorExpression.class),
            new EntityObjectMapper(MgBinaryOperatorExpression.class),
            new EntityObjectMapper(MgDeclarationExpression.class),
            new EntityObjectMapper(MgDereferenceOperatorExpression.class),
            new EntityObjectMapper(MgFunctionExpression.class),
            new EntityObjectMapper(MgLunaryOperatorExpression.class),
            new EntityObjectMapper(MgMemberExpression.class),
            new EntityObjectMapper(MgReferenceOperatorExpression.class),
            new EntityObjectMapper(MgRunaryOperatorExpression.class),
            new EntityObjectMapper(MgValueExpression.class),
            new EntityObjectMapper(MgVariableExpression.class)
        ));
    }
}
