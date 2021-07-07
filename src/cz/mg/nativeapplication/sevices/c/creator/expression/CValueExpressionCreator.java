package cz.mg.nativeapplication.sevices.c.creator.expression;

import cz.mg.nativeapplication.entities.mg.components.MgAtom;
import cz.mg.nativeapplication.entities.mg.parts.MgValue;
import cz.mg.nativeapplication.entities.mg.expression.MgValueExpression;
import cz.mg.nativeapplication.sevices.mg.MgAtomCreator;


public class CValueExpressionCreator {
    public String create(MgValueExpression expression){
        if(expression.value.value == null){
            return "null";
        }

        if(expression.value.type instanceof MgAtom){
            MgValue value = expression.value;
            MgAtom atom = (MgAtom) value.type;

            if(MgAtomCreator.SINT8_NAME.equals(atom.name)){
                return value.value.replace(" ", "");
            }

            if(MgAtomCreator.SINT16_NAME.equals(atom.name)){
                return value.value.replace(" ", "");
            }

            if(MgAtomCreator.SINT32_NAME.equals(atom.name)){
                return value.value.replace(" ", "");
            }

            if(MgAtomCreator.SINT64_NAME.equals(atom.name)){
                return value.value.replace(" ", "") + "L";
            }

            if(MgAtomCreator.UINT8_NAME.equals(atom.name)){
                return value.value.replace(" ", "") + "U";
            }

            if(MgAtomCreator.UINT16_NAME.equals(atom.name)){
                return value.value.replace(" ", "") + "U";
            }

            if(MgAtomCreator.UINT32_NAME.equals(atom.name)){
                return value.value.replace(" ", "") + "U";
            }

            if(MgAtomCreator.UINT64_NAME.equals(atom.name)){
                return value.value.replace(" ", "") + "UL";
            }

            if(MgAtomCreator.FLOAT32_NAME.equals(atom.name)){
                return value.value.replace(" ", "") + "F";
            }

            if(MgAtomCreator.FLOAT64_NAME.equals(atom.name)){
                return value.value.replace(" ", "");
            }

            if(MgAtomCreator.BOOL8_NAME.equals(atom.name)){
                if(value.value.equals("true")) return "1";
                if(value.value.equals("false")) return "0";
                throw new IllegalArgumentException("Illegal boolean value '" + value.value + "'.");
            }

            if(MgAtomCreator.CHAR8_NAME.equals(atom.name)){
                return "'" + value.value + "'";
            }

            if(MgAtomCreator.TEXT_NAME.equals(atom.name)){
                return '"' + value.value + '"';
            }

            throw new UnsupportedOperationException("Unsupported atom " + atom.name + " in c value expression creator.");
        }

        throw new UnsupportedOperationException("Unsupported value type " + expression.value.type.getClass().getSimpleName() + " in c value expression creator.");
    }
}
