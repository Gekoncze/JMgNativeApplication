package cz.mg.nativeapplication.sevices.mg;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.mg.components.MgAtom;
import cz.mg.nativeapplication.entities.mg.components.MgLocation;
import cz.mg.nativeapplication.entities.mg.parts.MgOperator;
import cz.mg.nativeapplication.entities.mg.parts.MgVariable;
import cz.mg.nativeapplication.entities.mg.existing.MgExistingFunction;


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

    public void create(MgLocation atoms){
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

        MgExistingFunction SINT8_POSITIVE = createExistingFunction(POSITIVE_OPERATOR, null, SINT8, SINT8);
        MgExistingFunction SINT8_NEGATIVE = createExistingFunction(NEGATIVE_OPERATOR, null, SINT8, SINT8);
        MgExistingFunction SINT8_PLUS = createExistingFunction(PLUS_OPERATOR, SINT8, SINT8, SINT8);
        MgExistingFunction SINT8_MINUS = createExistingFunction(MINUS_OPERATOR, SINT8, SINT8, SINT8);
        MgExistingFunction SINT8_MULTIPLY = createExistingFunction(MULTIPLY_OPERATOR, SINT8, SINT8, SINT8);
        MgExistingFunction SINT8_DIVIDE = createExistingFunction(DIVIDE_OPERATOR, SINT8, SINT8, SINT8);
        MgExistingFunction SINT8_SMALLER = createExistingFunction(SMALLER_OPERATOR, SINT8, SINT8, BOOL8);
        MgExistingFunction SINT8_GREATER = createExistingFunction(GREATER_OPERATOR, SINT8, SINT8, BOOL8);
        MgExistingFunction SINT8_EQUAL = createExistingFunction(EQUAL_OPERATOR, SINT8, SINT8, BOOL8);
        MgExistingFunction SINT8_NOT_EQUAL = createExistingFunction(NOT_EQUAL_OPERATOR, SINT8, SINT8, BOOL8);
        MgExistingFunction SINT8_SMALLER_OR_EQUAL = createExistingFunction(SMALLER_OR_EQUAL_OPERATOR, SINT8, SINT8, BOOL8);
        MgExistingFunction SINT8_GREATER_OR_EQUAL = createExistingFunction(GREATER_OR_EQUAL_OPERATOR, SINT8, SINT8, BOOL8);

        MgExistingFunction SINT16_POSITIVE = createExistingFunction(POSITIVE_OPERATOR, null, SINT16, SINT16);
        MgExistingFunction SINT16_NEGATIVE = createExistingFunction(NEGATIVE_OPERATOR, null, SINT16, SINT16);
        MgExistingFunction SINT16_PLUS = createExistingFunction(PLUS_OPERATOR, SINT16, SINT16, SINT16);
        MgExistingFunction SINT16_MINUS = createExistingFunction(MINUS_OPERATOR, SINT16, SINT16, SINT16);
        MgExistingFunction SINT16_MULTIPLY = createExistingFunction(MULTIPLY_OPERATOR, SINT16, SINT16, SINT16);
        MgExistingFunction SINT16_DIVIDE = createExistingFunction(DIVIDE_OPERATOR, SINT16, SINT16, SINT16);
        MgExistingFunction SINT16_SMALLER = createExistingFunction(SMALLER_OPERATOR, SINT16, SINT16, BOOL8);
        MgExistingFunction SINT16_GREATER = createExistingFunction(GREATER_OPERATOR, SINT16, SINT16, BOOL8);
        MgExistingFunction SINT16_EQUAL = createExistingFunction(EQUAL_OPERATOR, SINT16, SINT16, BOOL8);
        MgExistingFunction SINT16_NOT_EQUAL = createExistingFunction(NOT_EQUAL_OPERATOR, SINT16, SINT16, BOOL8);
        MgExistingFunction SINT16_SMALLER_OR_EQUAL = createExistingFunction(SMALLER_OR_EQUAL_OPERATOR, SINT16, SINT16, BOOL8);
        MgExistingFunction SINT16_GREATER_OR_EQUAL = createExistingFunction(GREATER_OR_EQUAL_OPERATOR, SINT16, SINT16, BOOL8);

        MgExistingFunction SINT32_POSITIVE = createExistingFunction(POSITIVE_OPERATOR, null, SINT32, SINT32);
        MgExistingFunction SINT32_NEGATIVE = createExistingFunction(NEGATIVE_OPERATOR, null, SINT32, SINT32);
        MgExistingFunction SINT32_PLUS = createExistingFunction(PLUS_OPERATOR, SINT32, SINT32, SINT32);
        MgExistingFunction SINT32_MINUS = createExistingFunction(MINUS_OPERATOR, SINT32, SINT32, SINT32);
        MgExistingFunction SINT32_MULTIPLY = createExistingFunction(MULTIPLY_OPERATOR, SINT32, SINT32, SINT32);
        MgExistingFunction SINT32_DIVIDE = createExistingFunction(DIVIDE_OPERATOR, SINT32, SINT32, SINT32);
        MgExistingFunction SINT32_SMALLER = createExistingFunction(SMALLER_OPERATOR, SINT32, SINT32, BOOL8);
        MgExistingFunction SINT32_GREATER = createExistingFunction(GREATER_OPERATOR, SINT32, SINT32, BOOL8);
        MgExistingFunction SINT32_EQUAL = createExistingFunction(EQUAL_OPERATOR, SINT32, SINT32, BOOL8);
        MgExistingFunction SINT32_NOT_EQUAL = createExistingFunction(NOT_EQUAL_OPERATOR, SINT32, SINT32, BOOL8);
        MgExistingFunction SINT32_SMALLER_OR_EQUAL = createExistingFunction(SMALLER_OR_EQUAL_OPERATOR, SINT32, SINT32, BOOL8);
        MgExistingFunction SINT32_GREATER_OR_EQUAL = createExistingFunction(GREATER_OR_EQUAL_OPERATOR, SINT32, SINT32, BOOL8);

        MgExistingFunction SINT64_POSITIVE = createExistingFunction(POSITIVE_OPERATOR, null, SINT64, SINT64);
        MgExistingFunction SINT64_NEGATIVE = createExistingFunction(NEGATIVE_OPERATOR, null, SINT64, SINT64);
        MgExistingFunction SINT64_PLUS = createExistingFunction(PLUS_OPERATOR, SINT64, SINT64, SINT64);
        MgExistingFunction SINT64_MINUS = createExistingFunction(MINUS_OPERATOR, SINT64, SINT64, SINT64);
        MgExistingFunction SINT64_MULTIPLY = createExistingFunction(MULTIPLY_OPERATOR, SINT64, SINT64, SINT64);
        MgExistingFunction SINT64_DIVIDE = createExistingFunction(DIVIDE_OPERATOR, SINT64, SINT64, SINT64);
        MgExistingFunction SINT64_SMALLER = createExistingFunction(SMALLER_OPERATOR, SINT64, SINT64, BOOL8);
        MgExistingFunction SINT64_GREATER = createExistingFunction(GREATER_OPERATOR, SINT64, SINT64, BOOL8);
        MgExistingFunction SINT64_EQUAL = createExistingFunction(EQUAL_OPERATOR, SINT64, SINT64, BOOL8);
        MgExistingFunction SINT64_NOT_EQUAL = createExistingFunction(NOT_EQUAL_OPERATOR, SINT64, SINT64, BOOL8);
        MgExistingFunction SINT64_SMALLER_OR_EQUAL = createExistingFunction(SMALLER_OR_EQUAL_OPERATOR, SINT64, SINT64, BOOL8);
        MgExistingFunction SINT64_GREATER_OR_EQUAL = createExistingFunction(GREATER_OR_EQUAL_OPERATOR, SINT64, SINT64, BOOL8);

        MgExistingFunction UINT8_POSITIVE = createExistingFunction(POSITIVE_OPERATOR, null, UINT8, UINT8);
        MgExistingFunction UINT8_NEGATIVE = createExistingFunction(NEGATIVE_OPERATOR, null, UINT8, UINT8);
        MgExistingFunction UINT8_PLUS = createExistingFunction(PLUS_OPERATOR, UINT8, UINT8, UINT8);
        MgExistingFunction UINT8_MINUS = createExistingFunction(MINUS_OPERATOR, UINT8, UINT8, UINT8);
        MgExistingFunction UINT8_MULTIPLY = createExistingFunction(MULTIPLY_OPERATOR, UINT8, UINT8, UINT8);
        MgExistingFunction UINT8_DIVIDE = createExistingFunction(DIVIDE_OPERATOR, UINT8, UINT8, UINT8);
        MgExistingFunction UINT8_SMALLER = createExistingFunction(SMALLER_OPERATOR, UINT8, UINT8, BOOL8);
        MgExistingFunction UINT8_GREATER = createExistingFunction(GREATER_OPERATOR, UINT8, UINT8, BOOL8);
        MgExistingFunction UINT8_EQUAL = createExistingFunction(EQUAL_OPERATOR, UINT8, UINT8, BOOL8);
        MgExistingFunction UINT8_NOT_EQUAL = createExistingFunction(NOT_EQUAL_OPERATOR, UINT8, UINT8, BOOL8);
        MgExistingFunction UINT8_SMALLER_OR_EQUAL = createExistingFunction(SMALLER_OR_EQUAL_OPERATOR, UINT8, UINT8, BOOL8);
        MgExistingFunction UINT8_GREATER_OR_EQUAL = createExistingFunction(GREATER_OR_EQUAL_OPERATOR, UINT8, UINT8, BOOL8);

        MgExistingFunction UINT16_POSITIVE = createExistingFunction(POSITIVE_OPERATOR, null, UINT16, UINT16);
        MgExistingFunction UINT16_NEGATIVE = createExistingFunction(NEGATIVE_OPERATOR, null, UINT16, UINT16);
        MgExistingFunction UINT16_PLUS = createExistingFunction(PLUS_OPERATOR, UINT16, UINT16, UINT16);
        MgExistingFunction UINT16_MINUS = createExistingFunction(MINUS_OPERATOR, UINT16, UINT16, UINT16);
        MgExistingFunction UINT16_MULTIPLY = createExistingFunction(MULTIPLY_OPERATOR, UINT16, UINT16, UINT16);
        MgExistingFunction UINT16_DIVIDE = createExistingFunction(DIVIDE_OPERATOR, UINT16, UINT16, UINT16);
        MgExistingFunction UINT16_SMALLER = createExistingFunction(SMALLER_OPERATOR, UINT16, UINT16, BOOL8);
        MgExistingFunction UINT16_GREATER = createExistingFunction(GREATER_OPERATOR, UINT16, UINT16, BOOL8);
        MgExistingFunction UINT16_EQUAL = createExistingFunction(EQUAL_OPERATOR, UINT16, UINT16, BOOL8);
        MgExistingFunction UINT16_NOT_EQUAL = createExistingFunction(NOT_EQUAL_OPERATOR, UINT16, UINT16, BOOL8);
        MgExistingFunction UINT16_SMALLER_OR_EQUAL = createExistingFunction(SMALLER_OR_EQUAL_OPERATOR, UINT16, UINT16, BOOL8);
        MgExistingFunction UINT16_GREATER_OR_EQUAL = createExistingFunction(GREATER_OR_EQUAL_OPERATOR, UINT16, UINT16, BOOL8);

        MgExistingFunction UINT32_POSITIVE = createExistingFunction(POSITIVE_OPERATOR, null, UINT32, UINT32);
        MgExistingFunction UINT32_NEGATIVE = createExistingFunction(NEGATIVE_OPERATOR, null, UINT32, UINT32);
        MgExistingFunction UINT32_PLUS = createExistingFunction(PLUS_OPERATOR, UINT32, UINT32, UINT32);
        MgExistingFunction UINT32_MINUS = createExistingFunction(MINUS_OPERATOR, UINT32, UINT32, UINT32);
        MgExistingFunction UINT32_MULTIPLY = createExistingFunction(MULTIPLY_OPERATOR, UINT32, UINT32, UINT32);
        MgExistingFunction UINT32_DIVIDE = createExistingFunction(DIVIDE_OPERATOR, UINT32, UINT32, UINT32);
        MgExistingFunction UINT32_SMALLER = createExistingFunction(SMALLER_OPERATOR, UINT32, UINT32, BOOL8);
        MgExistingFunction UINT32_GREATER = createExistingFunction(GREATER_OPERATOR, UINT32, UINT32, BOOL8);
        MgExistingFunction UINT32_EQUAL = createExistingFunction(EQUAL_OPERATOR, UINT32, UINT32, BOOL8);
        MgExistingFunction UINT32_NOT_EQUAL = createExistingFunction(NOT_EQUAL_OPERATOR, UINT32, UINT32, BOOL8);
        MgExistingFunction UINT32_SMALLER_OR_EQUAL = createExistingFunction(SMALLER_OR_EQUAL_OPERATOR, UINT32, UINT32, BOOL8);
        MgExistingFunction UINT32_GREATER_OR_EQUAL = createExistingFunction(GREATER_OR_EQUAL_OPERATOR, UINT32, UINT32, BOOL8);

        MgExistingFunction UINT64_POSITIVE = createExistingFunction(POSITIVE_OPERATOR, null, UINT64, UINT64);
        MgExistingFunction UINT64_NEGATIVE = createExistingFunction(NEGATIVE_OPERATOR, null, UINT64, UINT64);
        MgExistingFunction UINT64_PLUS = createExistingFunction(PLUS_OPERATOR, UINT64, UINT64, UINT64);
        MgExistingFunction UINT64_MINUS = createExistingFunction(MINUS_OPERATOR, UINT64, UINT64, UINT64);
        MgExistingFunction UINT64_MULTIPLY = createExistingFunction(MULTIPLY_OPERATOR, UINT64, UINT64, UINT64);
        MgExistingFunction UINT64_DIVIDE = createExistingFunction(DIVIDE_OPERATOR, UINT64, UINT64, UINT64);
        MgExistingFunction UINT64_SMALLER = createExistingFunction(SMALLER_OPERATOR, UINT64, UINT64, BOOL8);
        MgExistingFunction UINT64_GREATER = createExistingFunction(GREATER_OPERATOR, UINT64, UINT64, BOOL8);
        MgExistingFunction UINT64_EQUAL = createExistingFunction(EQUAL_OPERATOR, UINT64, UINT64, BOOL8);
        MgExistingFunction UINT64_NOT_EQUAL = createExistingFunction(NOT_EQUAL_OPERATOR, UINT64, UINT64, BOOL8);
        MgExistingFunction UINT64_SMALLER_OR_EQUAL = createExistingFunction(SMALLER_OR_EQUAL_OPERATOR, UINT64, UINT64, BOOL8);
        MgExistingFunction UINT64_GREATER_OR_EQUAL = createExistingFunction(GREATER_OR_EQUAL_OPERATOR, UINT64, UINT64, BOOL8);

        MgExistingFunction FLOAT32_POSITIVE = createExistingFunction(POSITIVE_OPERATOR, null, FLOAT32, FLOAT32);
        MgExistingFunction FLOAT32_NEGATIVE = createExistingFunction(NEGATIVE_OPERATOR, null, FLOAT32, FLOAT32);
        MgExistingFunction FLOAT32_PLUS = createExistingFunction(PLUS_OPERATOR, FLOAT32, FLOAT32, FLOAT32);
        MgExistingFunction FLOAT32_MINUS = createExistingFunction(MINUS_OPERATOR, FLOAT32, FLOAT32, FLOAT32);
        MgExistingFunction FLOAT32_MULTIPLY = createExistingFunction(MULTIPLY_OPERATOR, FLOAT32, FLOAT32, FLOAT32);
        MgExistingFunction FLOAT32_DIVIDE = createExistingFunction(DIVIDE_OPERATOR, FLOAT32, FLOAT32, FLOAT32);
        MgExistingFunction FLOAT32_SMALLER = createExistingFunction(SMALLER_OPERATOR, FLOAT32, FLOAT32, BOOL8);
        MgExistingFunction FLOAT32_GREATER = createExistingFunction(GREATER_OPERATOR, FLOAT32, FLOAT32, BOOL8);
        MgExistingFunction FLOAT32_EQUAL = createExistingFunction(EQUAL_OPERATOR, FLOAT32, FLOAT32, BOOL8);
        MgExistingFunction FLOAT32_NOT_EQUAL = createExistingFunction(NOT_EQUAL_OPERATOR, FLOAT32, FLOAT32, BOOL8);
        MgExistingFunction FLOAT32_SMALLER_OR_EQUAL = createExistingFunction(SMALLER_OR_EQUAL_OPERATOR, FLOAT32, FLOAT32, BOOL8);
        MgExistingFunction FLOAT32_GREATER_OR_EQUAL = createExistingFunction(GREATER_OR_EQUAL_OPERATOR, FLOAT32, FLOAT32, BOOL8);

        MgExistingFunction FLOAT64_POSITIVE = createExistingFunction(POSITIVE_OPERATOR, null, FLOAT64, FLOAT64);
        MgExistingFunction FLOAT64_NEGATIVE = createExistingFunction(NEGATIVE_OPERATOR, null, FLOAT64, FLOAT64);
        MgExistingFunction FLOAT64_PLUS = createExistingFunction(PLUS_OPERATOR, FLOAT64, FLOAT64, FLOAT64);
        MgExistingFunction FLOAT64_MINUS = createExistingFunction(MINUS_OPERATOR, FLOAT64, FLOAT64, FLOAT64);
        MgExistingFunction FLOAT64_MULTIPLY = createExistingFunction(MULTIPLY_OPERATOR, FLOAT64, FLOAT64, FLOAT64);
        MgExistingFunction FLOAT64_DIVIDE = createExistingFunction(DIVIDE_OPERATOR, FLOAT64, FLOAT64, FLOAT64);
        MgExistingFunction FLOAT64_SMALLER = createExistingFunction(SMALLER_OPERATOR, FLOAT64, FLOAT64, BOOL8);
        MgExistingFunction FLOAT64_GREATER = createExistingFunction(GREATER_OPERATOR, FLOAT64, FLOAT64, BOOL8);
        MgExistingFunction FLOAT64_EQUAL = createExistingFunction(EQUAL_OPERATOR, FLOAT64, FLOAT64, BOOL8);
        MgExistingFunction FLOAT64_NOT_EQUAL = createExistingFunction(NOT_EQUAL_OPERATOR, FLOAT64, FLOAT64, BOOL8);
        MgExistingFunction FLOAT64_SMALLER_OR_EQUAL = createExistingFunction(SMALLER_OR_EQUAL_OPERATOR, FLOAT64, FLOAT64, BOOL8);
        MgExistingFunction FLOAT64_GREATER_OR_EQUAL = createExistingFunction(GREATER_OR_EQUAL_OPERATOR, FLOAT64, FLOAT64, BOOL8);

        MgExistingFunction BOOL8_AND = createExistingFunction(LOGICAL_AND_OPERATOR, BOOL8, BOOL8, BOOL8);
        MgExistingFunction BOOL8_OR = createExistingFunction(LOGICAL_OR_OPERATOR, BOOL8, BOOL8, BOOL8);
        MgExistingFunction BOOL8_NOT = createExistingFunction(LOGICAL_NOT_OPERATOR, null, BOOL8, BOOL8);
        MgExistingFunction BOOL8_EQUAL = createExistingFunction(EQUAL_OPERATOR, BOOL8, BOOL8, BOOL8);
        MgExistingFunction BOOL8_NOT_EQUAL = createExistingFunction(NOT_EQUAL_OPERATOR, BOOL8, BOOL8, BOOL8);

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

    private static MgAtom createAtom(String name){
        MgAtom atom = new MgAtom();
        atom.name = name;
        return atom;
    }

    private static MgExistingFunction createExistingFunction(String signs, MgAtom left, MgAtom right, MgAtom result){
        MgExistingFunction function = new MgExistingFunction();
        function.operator = new MgOperator();
        function.operator.signs = signs;
        function.operator.type = left != null && right != null ? MgOperator.Type.BINARY : (
            left != null ? MgOperator.Type.RUNARY : (
                right != null ? MgOperator.Type.LUNARY : null
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

    private static MgVariable createVariable(MgAtom atom, String name){
        MgVariable variable = new MgVariable();
        variable.type = atom;
        variable.pointers = 0;
        variable.name = name;
        return variable;
    }
}
