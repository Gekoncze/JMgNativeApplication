package cz.mg.nativeapplication.sevices.c.creator;

import cz.mg.annotations.classes.Service;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.c.*;
import cz.mg.nativeapplication.entities.mg.components.MgFunction;


public @Service class CMainCreator {
    public CFile create(MgFunction main){
        CFile mainFile = new CFile();
        CFunctionDefinition mainFunction = new CFunctionDefinition();

        CVariable argc = new CVariable();
        argc.type = new CType();
        argc.type.structure = false;
        argc.type.name = "int";
        argc.type.pointers = 0;
        argc.name = "argc";

        CVariable argv = new CVariable();
        argv.type.structure = false;
        argv.type.name = "char";
        argv.type.pointers = 2;
        argv.name = "argv";

        CType output = new CType();
        output.structure = false;
        output.name = "int";
        output.pointers = 0;

        mainFunction.declaration.name = "main";
        mainFunction.declaration.input = new List<>(argc, argv);
        mainFunction.declaration.output = output;

        CCommand command = new CCommand();
        command.expression = "return " + main.name + "(argc, argv)";

        mainFunction.commands.addLast(command);
        mainFile.components.addLast(mainFunction);

        return mainFile;
    }
}
