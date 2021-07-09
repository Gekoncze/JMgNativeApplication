package cz.mg.nativeapplication.sevices.c.creator;

import cz.mg.annotations.classes.Service;
import cz.mg.nativeapplication.entities.c.CFile;
import cz.mg.nativeapplication.entities.c.CFolder;
import cz.mg.nativeapplication.entities.c.CFunctionDeclaration;
import cz.mg.nativeapplication.entities.c.CFunctionDefinition;
import cz.mg.nativeapplication.entities.mg.command.MgCommand;
import cz.mg.nativeapplication.entities.mg.components.MgFunction;
import cz.mg.nativeapplication.entities.mg.components.MgVariable;
import cz.mg.nativeapplication.sevices.c.creator.command.CCommandCreator;


public @Service class CFunctionCreator {
    public void create(CFolder cFolder, MgFunction mgFunction){
        CFile headerFile = new CFile();
        headerFile.name = mgFunction.name + ".h";

        CFunctionDeclaration declaration = new CFunctionDeclaration();
        declaration.name = mgFunction.name;
        declaration.output = new CTypeCreator().create(mgFunction.output);
        for(MgVariable mgInput : mgFunction.input){
            declaration.input.addLast(new CVariableCreator().create(mgInput));
        }

        headerFile.components.addLast(declaration);

        CFile sourceFile = new CFile();
        sourceFile.name = mgFunction.name + ".c";

        CFunctionDefinition definition = new CFunctionDefinition();
        definition.declaration = declaration;
        for(MgCommand mgCommand : mgFunction.commands){
            definition.commands.addCollectionLast(new CCommandCreator().create(mgCommand));
        }

        sourceFile.components.addLast(definition);

        cFolder.files.addLast(headerFile);
        cFolder.files.addLast(sourceFile);
    }
}
