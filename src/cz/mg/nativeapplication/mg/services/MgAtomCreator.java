package cz.mg.nativeapplication.mg.services;

import cz.mg.annotations.classes.Service;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.nativeapplication.mg.entities.components.MgAtom;
import cz.mg.nativeapplication.mg.entities.components.MgFunction;
import cz.mg.nativeapplication.mg.entities.components.MgLocation;
import cz.mg.nativeapplication.mg.entities.parts.MgOperator;
import cz.mg.nativeapplication.mg.entities.components.MgVariable;
import cz.mg.nativeapplication.mg.entities.parts.MgOperatorType;


public @Service class MgAtomCreator {
    public static final String SINT8_NAME = "SInt8";
    public static final String SINT16_NAME = "SInt16";
    public static final String SINT32_NAME = "SInt32";
    public static final String SINT64_NAME = "SInt64";
    public static final String UINT8_NAME = "UInt8";
    public static final String UINT16_NAME = "UInt16";
    public static final String UINT32_NAME = "UInt32";
    public static final String UINT64_NAME = "UInt64";
    public static final String FLOAT32_NAME = "Float32";
    public static final String FLOAT64_NAME = "Float64";
    public static final String BOOL8_NAME = "Bool8";
    public static final String CHAR8_NAME = "Char8";
    public static final String TEXT_NAME = "Text";

    public static final String POSITIVE_OPERATOR = "+";
    public static final String NEGATIVE_OPERATOR = "-";
    public static final String PLUS_OPERATOR = "+";
    public static final String MINUS_OPERATOR = "-";
    public static final String MULTIPLY_OPERATOR = "*";
    public static final String DIVIDE_OPERATOR = "/";
    public static final String SMALLER_OPERATOR = "<";
    public static final String GREATER_OPERATOR = ">";
    public static final String EQUAL_OPERATOR = "==";
    public static final String NOT_EQUAL_OPERATOR = "!=";
    public static final String SMALLER_OR_EQUAL_OPERATOR = "<=";
    public static final String GREATER_OR_EQUAL_OPERATOR = ">=";
    public static final String LOGICAL_AND_OPERATOR = "&&";
    public static final String LOGICAL_OR_OPERATOR = "||";
    public static final String LOGICAL_NOT_OPERATOR = "!";

    public void create(@Mandatory MgLocation atoms){
        MgAtom SINT8 = createAtom(SINT8_NAME);
        MgAtom SINT16 = createAtom(SINT16_NAME);
        MgAtom SINT32 = createAtom(SINT32_NAME);
        MgAtom SINT64 = createAtom(SINT64_NAME);
        MgAtom UINT8 = createAtom(UINT8_NAME);
        MgAtom UINT16 = createAtom(UINT16_NAME);
        MgAtom UINT32 = createAtom(UINT32_NAME);
        MgAtom UINT64 = createAtom(UINT64_NAME);
        MgAtom FLOAT32 = createAtom(FLOAT32_NAME);
        MgAtom FLOAT64 = createAtom(FLOAT64_NAME);
        MgAtom BOOL8 = createAtom(BOOL8_NAME);
        MgAtom CHAR8 = createAtom(CHAR8_NAME);
        MgAtom TEXT = createAtom(TEXT_NAME);

        atoms.components.addLast(SINT8);
        atoms.components.addLast(SINT16);
        atoms.components.addLast(SINT32);
        atoms.components.addLast(SINT64);
        atoms.components.addLast(UINT8);
        atoms.components.addLast(UINT16);
        atoms.components.addLast(UINT32);
        atoms.components.addLast(UINT64);
        atoms.components.addLast(FLOAT32);
        atoms.components.addLast(FLOAT64);
        atoms.components.addLast(BOOL8);
        atoms.components.addLast(CHAR8);
        atoms.components.addLast(TEXT);

        MgLocation operators = new MgLocation();
        operators.name = "operators";
        operators.external = false;

        MgFunction SINT8_POSITIVE = createFunction(POSITIVE_OPERATOR, null, SINT8, SINT8);
        MgFunction SINT8_NEGATIVE = createFunction(NEGATIVE_OPERATOR, null, SINT8, SINT8);
        MgFunction SINT8_PLUS = createFunction(PLUS_OPERATOR, SINT8, SINT8, SINT8);
        MgFunction SINT8_MINUS = createFunction(MINUS_OPERATOR, SINT8, SINT8, SINT8);
        MgFunction SINT8_MULTIPLY = createFunction(MULTIPLY_OPERATOR, SINT8, SINT8, SINT8);
        MgFunction SINT8_DIVIDE = createFunction(DIVIDE_OPERATOR, SINT8, SINT8, SINT8);
        MgFunction SINT8_SMALLER = createFunction(SMALLER_OPERATOR, SINT8, SINT8, BOOL8);
        MgFunction SINT8_GREATER = createFunction(GREATER_OPERATOR, SINT8, SINT8, BOOL8);
        MgFunction SINT8_EQUAL = createFunction(EQUAL_OPERATOR, SINT8, SINT8, BOOL8);
        MgFunction SINT8_NOT_EQUAL = createFunction(NOT_EQUAL_OPERATOR, SINT8, SINT8, BOOL8);
        MgFunction SINT8_SMALLER_OR_EQUAL = createFunction(SMALLER_OR_EQUAL_OPERATOR, SINT8, SINT8, BOOL8);
        MgFunction SINT8_GREATER_OR_EQUAL = createFunction(GREATER_OR_EQUAL_OPERATOR, SINT8, SINT8, BOOL8);

        MgFunction SINT16_POSITIVE = createFunction(POSITIVE_OPERATOR, null, SINT16, SINT16);
        MgFunction SINT16_NEGATIVE = createFunction(NEGATIVE_OPERATOR, null, SINT16, SINT16);
        MgFunction SINT16_PLUS = createFunction(PLUS_OPERATOR, SINT16, SINT16, SINT16);
        MgFunction SINT16_MINUS = createFunction(MINUS_OPERATOR, SINT16, SINT16, SINT16);
        MgFunction SINT16_MULTIPLY = createFunction(MULTIPLY_OPERATOR, SINT16, SINT16, SINT16);
        MgFunction SINT16_DIVIDE = createFunction(DIVIDE_OPERATOR, SINT16, SINT16, SINT16);
        MgFunction SINT16_SMALLER = createFunction(SMALLER_OPERATOR, SINT16, SINT16, BOOL8);
        MgFunction SINT16_GREATER = createFunction(GREATER_OPERATOR, SINT16, SINT16, BOOL8);
        MgFunction SINT16_EQUAL = createFunction(EQUAL_OPERATOR, SINT16, SINT16, BOOL8);
        MgFunction SINT16_NOT_EQUAL = createFunction(NOT_EQUAL_OPERATOR, SINT16, SINT16, BOOL8);
        MgFunction SINT16_SMALLER_OR_EQUAL = createFunction(SMALLER_OR_EQUAL_OPERATOR, SINT16, SINT16, BOOL8);
        MgFunction SINT16_GREATER_OR_EQUAL = createFunction(GREATER_OR_EQUAL_OPERATOR, SINT16, SINT16, BOOL8);

        MgFunction SINT32_POSITIVE = createFunction(POSITIVE_OPERATOR, null, SINT32, SINT32);
        MgFunction SINT32_NEGATIVE = createFunction(NEGATIVE_OPERATOR, null, SINT32, SINT32);
        MgFunction SINT32_PLUS = createFunction(PLUS_OPERATOR, SINT32, SINT32, SINT32);
        MgFunction SINT32_MINUS = createFunction(MINUS_OPERATOR, SINT32, SINT32, SINT32);
        MgFunction SINT32_MULTIPLY = createFunction(MULTIPLY_OPERATOR, SINT32, SINT32, SINT32);
        MgFunction SINT32_DIVIDE = createFunction(DIVIDE_OPERATOR, SINT32, SINT32, SINT32);
        MgFunction SINT32_SMALLER = createFunction(SMALLER_OPERATOR, SINT32, SINT32, BOOL8);
        MgFunction SINT32_GREATER = createFunction(GREATER_OPERATOR, SINT32, SINT32, BOOL8);
        MgFunction SINT32_EQUAL = createFunction(EQUAL_OPERATOR, SINT32, SINT32, BOOL8);
        MgFunction SINT32_NOT_EQUAL = createFunction(NOT_EQUAL_OPERATOR, SINT32, SINT32, BOOL8);
        MgFunction SINT32_SMALLER_OR_EQUAL = createFunction(SMALLER_OR_EQUAL_OPERATOR, SINT32, SINT32, BOOL8);
        MgFunction SINT32_GREATER_OR_EQUAL = createFunction(GREATER_OR_EQUAL_OPERATOR, SINT32, SINT32, BOOL8);

        MgFunction SINT64_POSITIVE = createFunction(POSITIVE_OPERATOR, null, SINT64, SINT64);
        MgFunction SINT64_NEGATIVE = createFunction(NEGATIVE_OPERATOR, null, SINT64, SINT64);
        MgFunction SINT64_PLUS = createFunction(PLUS_OPERATOR, SINT64, SINT64, SINT64);
        MgFunction SINT64_MINUS = createFunction(MINUS_OPERATOR, SINT64, SINT64, SINT64);
        MgFunction SINT64_MULTIPLY = createFunction(MULTIPLY_OPERATOR, SINT64, SINT64, SINT64);
        MgFunction SINT64_DIVIDE = createFunction(DIVIDE_OPERATOR, SINT64, SINT64, SINT64);
        MgFunction SINT64_SMALLER = createFunction(SMALLER_OPERATOR, SINT64, SINT64, BOOL8);
        MgFunction SINT64_GREATER = createFunction(GREATER_OPERATOR, SINT64, SINT64, BOOL8);
        MgFunction SINT64_EQUAL = createFunction(EQUAL_OPERATOR, SINT64, SINT64, BOOL8);
        MgFunction SINT64_NOT_EQUAL = createFunction(NOT_EQUAL_OPERATOR, SINT64, SINT64, BOOL8);
        MgFunction SINT64_SMALLER_OR_EQUAL = createFunction(SMALLER_OR_EQUAL_OPERATOR, SINT64, SINT64, BOOL8);
        MgFunction SINT64_GREATER_OR_EQUAL = createFunction(GREATER_OR_EQUAL_OPERATOR, SINT64, SINT64, BOOL8);

        MgFunction UINT8_POSITIVE = createFunction(POSITIVE_OPERATOR, null, UINT8, UINT8);
        MgFunction UINT8_NEGATIVE = createFunction(NEGATIVE_OPERATOR, null, UINT8, UINT8);
        MgFunction UINT8_PLUS = createFunction(PLUS_OPERATOR, UINT8, UINT8, UINT8);
        MgFunction UINT8_MINUS = createFunction(MINUS_OPERATOR, UINT8, UINT8, UINT8);
        MgFunction UINT8_MULTIPLY = createFunction(MULTIPLY_OPERATOR, UINT8, UINT8, UINT8);
        MgFunction UINT8_DIVIDE = createFunction(DIVIDE_OPERATOR, UINT8, UINT8, UINT8);
        MgFunction UINT8_SMALLER = createFunction(SMALLER_OPERATOR, UINT8, UINT8, BOOL8);
        MgFunction UINT8_GREATER = createFunction(GREATER_OPERATOR, UINT8, UINT8, BOOL8);
        MgFunction UINT8_EQUAL = createFunction(EQUAL_OPERATOR, UINT8, UINT8, BOOL8);
        MgFunction UINT8_NOT_EQUAL = createFunction(NOT_EQUAL_OPERATOR, UINT8, UINT8, BOOL8);
        MgFunction UINT8_SMALLER_OR_EQUAL = createFunction(SMALLER_OR_EQUAL_OPERATOR, UINT8, UINT8, BOOL8);
        MgFunction UINT8_GREATER_OR_EQUAL = createFunction(GREATER_OR_EQUAL_OPERATOR, UINT8, UINT8, BOOL8);

        MgFunction UINT16_POSITIVE = createFunction(POSITIVE_OPERATOR, null, UINT16, UINT16);
        MgFunction UINT16_NEGATIVE = createFunction(NEGATIVE_OPERATOR, null, UINT16, UINT16);
        MgFunction UINT16_PLUS = createFunction(PLUS_OPERATOR, UINT16, UINT16, UINT16);
        MgFunction UINT16_MINUS = createFunction(MINUS_OPERATOR, UINT16, UINT16, UINT16);
        MgFunction UINT16_MULTIPLY = createFunction(MULTIPLY_OPERATOR, UINT16, UINT16, UINT16);
        MgFunction UINT16_DIVIDE = createFunction(DIVIDE_OPERATOR, UINT16, UINT16, UINT16);
        MgFunction UINT16_SMALLER = createFunction(SMALLER_OPERATOR, UINT16, UINT16, BOOL8);
        MgFunction UINT16_GREATER = createFunction(GREATER_OPERATOR, UINT16, UINT16, BOOL8);
        MgFunction UINT16_EQUAL = createFunction(EQUAL_OPERATOR, UINT16, UINT16, BOOL8);
        MgFunction UINT16_NOT_EQUAL = createFunction(NOT_EQUAL_OPERATOR, UINT16, UINT16, BOOL8);
        MgFunction UINT16_SMALLER_OR_EQUAL = createFunction(SMALLER_OR_EQUAL_OPERATOR, UINT16, UINT16, BOOL8);
        MgFunction UINT16_GREATER_OR_EQUAL = createFunction(GREATER_OR_EQUAL_OPERATOR, UINT16, UINT16, BOOL8);

        MgFunction UINT32_POSITIVE = createFunction(POSITIVE_OPERATOR, null, UINT32, UINT32);
        MgFunction UINT32_NEGATIVE = createFunction(NEGATIVE_OPERATOR, null, UINT32, UINT32);
        MgFunction UINT32_PLUS = createFunction(PLUS_OPERATOR, UINT32, UINT32, UINT32);
        MgFunction UINT32_MINUS = createFunction(MINUS_OPERATOR, UINT32, UINT32, UINT32);
        MgFunction UINT32_MULTIPLY = createFunction(MULTIPLY_OPERATOR, UINT32, UINT32, UINT32);
        MgFunction UINT32_DIVIDE = createFunction(DIVIDE_OPERATOR, UINT32, UINT32, UINT32);
        MgFunction UINT32_SMALLER = createFunction(SMALLER_OPERATOR, UINT32, UINT32, BOOL8);
        MgFunction UINT32_GREATER = createFunction(GREATER_OPERATOR, UINT32, UINT32, BOOL8);
        MgFunction UINT32_EQUAL = createFunction(EQUAL_OPERATOR, UINT32, UINT32, BOOL8);
        MgFunction UINT32_NOT_EQUAL = createFunction(NOT_EQUAL_OPERATOR, UINT32, UINT32, BOOL8);
        MgFunction UINT32_SMALLER_OR_EQUAL = createFunction(SMALLER_OR_EQUAL_OPERATOR, UINT32, UINT32, BOOL8);
        MgFunction UINT32_GREATER_OR_EQUAL = createFunction(GREATER_OR_EQUAL_OPERATOR, UINT32, UINT32, BOOL8);

        MgFunction UINT64_POSITIVE = createFunction(POSITIVE_OPERATOR, null, UINT64, UINT64);
        MgFunction UINT64_NEGATIVE = createFunction(NEGATIVE_OPERATOR, null, UINT64, UINT64);
        MgFunction UINT64_PLUS = createFunction(PLUS_OPERATOR, UINT64, UINT64, UINT64);
        MgFunction UINT64_MINUS = createFunction(MINUS_OPERATOR, UINT64, UINT64, UINT64);
        MgFunction UINT64_MULTIPLY = createFunction(MULTIPLY_OPERATOR, UINT64, UINT64, UINT64);
        MgFunction UINT64_DIVIDE = createFunction(DIVIDE_OPERATOR, UINT64, UINT64, UINT64);
        MgFunction UINT64_SMALLER = createFunction(SMALLER_OPERATOR, UINT64, UINT64, BOOL8);
        MgFunction UINT64_GREATER = createFunction(GREATER_OPERATOR, UINT64, UINT64, BOOL8);
        MgFunction UINT64_EQUAL = createFunction(EQUAL_OPERATOR, UINT64, UINT64, BOOL8);
        MgFunction UINT64_NOT_EQUAL = createFunction(NOT_EQUAL_OPERATOR, UINT64, UINT64, BOOL8);
        MgFunction UINT64_SMALLER_OR_EQUAL = createFunction(SMALLER_OR_EQUAL_OPERATOR, UINT64, UINT64, BOOL8);
        MgFunction UINT64_GREATER_OR_EQUAL = createFunction(GREATER_OR_EQUAL_OPERATOR, UINT64, UINT64, BOOL8);

        MgFunction FLOAT32_POSITIVE = createFunction(POSITIVE_OPERATOR, null, FLOAT32, FLOAT32);
        MgFunction FLOAT32_NEGATIVE = createFunction(NEGATIVE_OPERATOR, null, FLOAT32, FLOAT32);
        MgFunction FLOAT32_PLUS = createFunction(PLUS_OPERATOR, FLOAT32, FLOAT32, FLOAT32);
        MgFunction FLOAT32_MINUS = createFunction(MINUS_OPERATOR, FLOAT32, FLOAT32, FLOAT32);
        MgFunction FLOAT32_MULTIPLY = createFunction(MULTIPLY_OPERATOR, FLOAT32, FLOAT32, FLOAT32);
        MgFunction FLOAT32_DIVIDE = createFunction(DIVIDE_OPERATOR, FLOAT32, FLOAT32, FLOAT32);
        MgFunction FLOAT32_SMALLER = createFunction(SMALLER_OPERATOR, FLOAT32, FLOAT32, BOOL8);
        MgFunction FLOAT32_GREATER = createFunction(GREATER_OPERATOR, FLOAT32, FLOAT32, BOOL8);
        MgFunction FLOAT32_EQUAL = createFunction(EQUAL_OPERATOR, FLOAT32, FLOAT32, BOOL8);
        MgFunction FLOAT32_NOT_EQUAL = createFunction(NOT_EQUAL_OPERATOR, FLOAT32, FLOAT32, BOOL8);
        MgFunction FLOAT32_SMALLER_OR_EQUAL = createFunction(SMALLER_OR_EQUAL_OPERATOR, FLOAT32, FLOAT32, BOOL8);
        MgFunction FLOAT32_GREATER_OR_EQUAL = createFunction(GREATER_OR_EQUAL_OPERATOR, FLOAT32, FLOAT32, BOOL8);

        MgFunction FLOAT64_POSITIVE = createFunction(POSITIVE_OPERATOR, null, FLOAT64, FLOAT64);
        MgFunction FLOAT64_NEGATIVE = createFunction(NEGATIVE_OPERATOR, null, FLOAT64, FLOAT64);
        MgFunction FLOAT64_PLUS = createFunction(PLUS_OPERATOR, FLOAT64, FLOAT64, FLOAT64);
        MgFunction FLOAT64_MINUS = createFunction(MINUS_OPERATOR, FLOAT64, FLOAT64, FLOAT64);
        MgFunction FLOAT64_MULTIPLY = createFunction(MULTIPLY_OPERATOR, FLOAT64, FLOAT64, FLOAT64);
        MgFunction FLOAT64_DIVIDE = createFunction(DIVIDE_OPERATOR, FLOAT64, FLOAT64, FLOAT64);
        MgFunction FLOAT64_SMALLER = createFunction(SMALLER_OPERATOR, FLOAT64, FLOAT64, BOOL8);
        MgFunction FLOAT64_GREATER = createFunction(GREATER_OPERATOR, FLOAT64, FLOAT64, BOOL8);
        MgFunction FLOAT64_EQUAL = createFunction(EQUAL_OPERATOR, FLOAT64, FLOAT64, BOOL8);
        MgFunction FLOAT64_NOT_EQUAL = createFunction(NOT_EQUAL_OPERATOR, FLOAT64, FLOAT64, BOOL8);
        MgFunction FLOAT64_SMALLER_OR_EQUAL = createFunction(SMALLER_OR_EQUAL_OPERATOR, FLOAT64, FLOAT64, BOOL8);
        MgFunction FLOAT64_GREATER_OR_EQUAL = createFunction(GREATER_OR_EQUAL_OPERATOR, FLOAT64, FLOAT64, BOOL8);

        MgFunction BOOL8_AND = createFunction(LOGICAL_AND_OPERATOR, BOOL8, BOOL8, BOOL8);
        MgFunction BOOL8_OR = createFunction(LOGICAL_OR_OPERATOR, BOOL8, BOOL8, BOOL8);
        MgFunction BOOL8_NOT = createFunction(LOGICAL_NOT_OPERATOR, null, BOOL8, BOOL8);
        MgFunction BOOL8_EQUAL = createFunction(EQUAL_OPERATOR, BOOL8, BOOL8, BOOL8);
        MgFunction BOOL8_NOT_EQUAL = createFunction(NOT_EQUAL_OPERATOR, BOOL8, BOOL8, BOOL8);

        operators.components.addLast(SINT8_POSITIVE);
        operators.components.addLast(SINT8_NEGATIVE);
        operators.components.addLast(SINT8_PLUS);
        operators.components.addLast(SINT8_MINUS);
        operators.components.addLast(SINT8_MULTIPLY);
        operators.components.addLast(SINT8_DIVIDE);
        operators.components.addLast(SINT8_SMALLER);
        operators.components.addLast(SINT8_GREATER);
        operators.components.addLast(SINT8_EQUAL);
        operators.components.addLast(SINT8_NOT_EQUAL);
        operators.components.addLast(SINT8_SMALLER_OR_EQUAL);
        operators.components.addLast(SINT8_GREATER_OR_EQUAL);

        operators.components.addLast(SINT16_POSITIVE);
        operators.components.addLast(SINT16_NEGATIVE);
        operators.components.addLast(SINT16_PLUS);
        operators.components.addLast(SINT16_MINUS);
        operators.components.addLast(SINT16_MULTIPLY);
        operators.components.addLast(SINT16_DIVIDE);
        operators.components.addLast(SINT16_SMALLER);
        operators.components.addLast(SINT16_GREATER);
        operators.components.addLast(SINT16_EQUAL);
        operators.components.addLast(SINT16_NOT_EQUAL);
        operators.components.addLast(SINT16_SMALLER_OR_EQUAL);
        operators.components.addLast(SINT16_GREATER_OR_EQUAL);

        operators.components.addLast(SINT32_POSITIVE);
        operators.components.addLast(SINT32_NEGATIVE);
        operators.components.addLast(SINT32_PLUS);
        operators.components.addLast(SINT32_MINUS);
        operators.components.addLast(SINT32_MULTIPLY);
        operators.components.addLast(SINT32_DIVIDE);
        operators.components.addLast(SINT32_SMALLER);
        operators.components.addLast(SINT32_GREATER);
        operators.components.addLast(SINT32_EQUAL);
        operators.components.addLast(SINT32_NOT_EQUAL);
        operators.components.addLast(SINT32_SMALLER_OR_EQUAL);
        operators.components.addLast(SINT32_GREATER_OR_EQUAL);

        operators.components.addLast(SINT64_POSITIVE);
        operators.components.addLast(SINT64_NEGATIVE);
        operators.components.addLast(SINT64_PLUS);
        operators.components.addLast(SINT64_MINUS);
        operators.components.addLast(SINT64_MULTIPLY);
        operators.components.addLast(SINT64_DIVIDE);
        operators.components.addLast(SINT64_SMALLER);
        operators.components.addLast(SINT64_GREATER);
        operators.components.addLast(SINT64_EQUAL);
        operators.components.addLast(SINT64_NOT_EQUAL);
        operators.components.addLast(SINT64_SMALLER_OR_EQUAL);
        operators.components.addLast(SINT64_GREATER_OR_EQUAL);

        operators.components.addLast(UINT8_POSITIVE);
        operators.components.addLast(UINT8_NEGATIVE);
        operators.components.addLast(UINT8_PLUS);
        operators.components.addLast(UINT8_MINUS);
        operators.components.addLast(UINT8_MULTIPLY);
        operators.components.addLast(UINT8_DIVIDE);
        operators.components.addLast(UINT8_SMALLER);
        operators.components.addLast(UINT8_GREATER);
        operators.components.addLast(UINT8_EQUAL);
        operators.components.addLast(UINT8_NOT_EQUAL);
        operators.components.addLast(UINT8_SMALLER_OR_EQUAL);
        operators.components.addLast(UINT8_GREATER_OR_EQUAL);

        operators.components.addLast(UINT16_POSITIVE);
        operators.components.addLast(UINT16_NEGATIVE);
        operators.components.addLast(UINT16_PLUS);
        operators.components.addLast(UINT16_MINUS);
        operators.components.addLast(UINT16_MULTIPLY);
        operators.components.addLast(UINT16_DIVIDE);
        operators.components.addLast(UINT16_SMALLER);
        operators.components.addLast(UINT16_GREATER);
        operators.components.addLast(UINT16_EQUAL);
        operators.components.addLast(UINT16_NOT_EQUAL);
        operators.components.addLast(UINT16_SMALLER_OR_EQUAL);
        operators.components.addLast(UINT16_GREATER_OR_EQUAL);

        operators.components.addLast(UINT32_POSITIVE);
        operators.components.addLast(UINT32_NEGATIVE);
        operators.components.addLast(UINT32_PLUS);
        operators.components.addLast(UINT32_MINUS);
        operators.components.addLast(UINT32_MULTIPLY);
        operators.components.addLast(UINT32_DIVIDE);
        operators.components.addLast(UINT32_SMALLER);
        operators.components.addLast(UINT32_GREATER);
        operators.components.addLast(UINT32_EQUAL);
        operators.components.addLast(UINT32_NOT_EQUAL);
        operators.components.addLast(UINT32_SMALLER_OR_EQUAL);
        operators.components.addLast(UINT32_GREATER_OR_EQUAL);

        operators.components.addLast(UINT64_POSITIVE);
        operators.components.addLast(UINT64_NEGATIVE);
        operators.components.addLast(UINT64_PLUS);
        operators.components.addLast(UINT64_MINUS);
        operators.components.addLast(UINT64_MULTIPLY);
        operators.components.addLast(UINT64_DIVIDE);
        operators.components.addLast(UINT64_SMALLER);
        operators.components.addLast(UINT64_GREATER);
        operators.components.addLast(UINT64_EQUAL);
        operators.components.addLast(UINT64_NOT_EQUAL);
        operators.components.addLast(UINT64_SMALLER_OR_EQUAL);
        operators.components.addLast(UINT64_GREATER_OR_EQUAL);

        operators.components.addLast(FLOAT32_POSITIVE);
        operators.components.addLast(FLOAT32_NEGATIVE);
        operators.components.addLast(FLOAT32_PLUS);
        operators.components.addLast(FLOAT32_MINUS);
        operators.components.addLast(FLOAT32_MULTIPLY);
        operators.components.addLast(FLOAT32_DIVIDE);
        operators.components.addLast(FLOAT32_SMALLER);
        operators.components.addLast(FLOAT32_GREATER);
        operators.components.addLast(FLOAT32_EQUAL);
        operators.components.addLast(FLOAT32_NOT_EQUAL);
        operators.components.addLast(FLOAT32_GREATER_OR_EQUAL);
        operators.components.addLast(FLOAT32_SMALLER_OR_EQUAL);

        operators.components.addLast(FLOAT64_POSITIVE);
        operators.components.addLast(FLOAT64_NEGATIVE);
        operators.components.addLast(FLOAT64_PLUS);
        operators.components.addLast(FLOAT64_MINUS);
        operators.components.addLast(FLOAT64_MULTIPLY);
        operators.components.addLast(FLOAT64_DIVIDE);
        operators.components.addLast(FLOAT64_SMALLER);
        operators.components.addLast(FLOAT64_GREATER);
        operators.components.addLast(FLOAT64_EQUAL);
        operators.components.addLast(FLOAT64_NOT_EQUAL);
        operators.components.addLast(FLOAT64_SMALLER_OR_EQUAL);
        operators.components.addLast(FLOAT64_GREATER_OR_EQUAL);

        operators.components.addFirst(BOOL8_AND);
        operators.components.addFirst(BOOL8_OR);
        operators.components.addFirst(BOOL8_NOT);
        operators.components.addFirst(BOOL8_EQUAL);
        operators.components.addFirst(BOOL8_NOT_EQUAL);

        atoms.components.addLast(operators);
    }

    private static @Mandatory MgAtom createAtom(@Mandatory String name){
        MgAtom atom = new MgAtom();
        atom.name = name;
        return atom;
    }

    private static @Mandatory MgFunction createFunction(
        @Mandatory String signs,
        @Optional MgAtom left,
        @Optional MgAtom right,
        @Optional MgAtom result
    ){
        MgFunction function = new MgFunction();
        function.operator = new MgOperator();
        function.operator.signs = signs;
        function.operator.type = left != null && right != null ? MgOperatorType.BINARY : (
            left != null ? MgOperatorType.RUNARY : (
                right != null ? MgOperatorType.LUNARY : null
            )
        );
        function.name = signs;
        if(left != null){
            function.input.addLast(createVariable(left, "left"));
        }
        if(right != null){
            function.input.addLast(createVariable(right, "right"));
        }
        if(result != null){
            function.output = createVariable(result, "result");
        }
        return function;
    }

    private static @Mandatory MgVariable createVariable(@Mandatory MgAtom atom, @Mandatory String name){
        MgVariable variable = new MgVariable();
        variable.type = atom;
        variable.pointers = 0;
        variable.name = name;
        return variable;
    }
}
