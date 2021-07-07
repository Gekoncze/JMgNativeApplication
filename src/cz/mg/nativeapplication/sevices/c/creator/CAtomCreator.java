package cz.mg.nativeapplication.sevices.c.creator;

import cz.mg.nativeapplication.entities.c.CDefine;
import cz.mg.nativeapplication.entities.c.CFile;
import cz.mg.nativeapplication.entities.c.CTypedef;

import static cz.mg.nativeapplication.sevices.mg.MgAtomCreator.*;


public class CAtomCreator {
    public CFile create(){
        CFile file = new CFile();
        file.name = "Atoms.h";
        file.components.addLast(createDefine("null", "((void*)0)"));
        file.components.addLast(createTypedef("char", SINT8_NAME));
        file.components.addLast(createTypedef("short int", SINT16_NAME));
        file.components.addLast(createTypedef("int", SINT32_NAME));
        file.components.addLast(createTypedef("long int", SINT64_NAME));
        file.components.addLast(createTypedef("unsigned char", UINT8_NAME));
        file.components.addLast(createTypedef("unsigned short int", UINT16_NAME));
        file.components.addLast(createTypedef("unsigned int", UINT32_NAME));
        file.components.addLast(createTypedef("unsigned long int", UINT64_NAME));
        file.components.addLast(createTypedef("float", FLOAT32_NAME));
        file.components.addLast(createTypedef("double", FLOAT64_NAME));
        file.components.addLast(createTypedef("unsigned char", BOOL8_NAME));
        file.components.addLast(createTypedef("char", CHAR8_NAME));
        return file;
    }

    private CDefine createDefine(String name, String definition){
        CDefine define = new CDefine();
        define.name = name;
        define.definition = definition;
        return define;
    }

    private CTypedef createTypedef(String type, String alias){
        CTypedef typedef = new CTypedef();
        typedef.type = type;
        typedef.alias = alias;
        return typedef;
    }
}
