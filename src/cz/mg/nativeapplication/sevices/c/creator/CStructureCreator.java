package cz.mg.nativeapplication.sevices.c.creator;

import cz.mg.annotations.classes.Service;
import cz.mg.collections.list.List;
import cz.mg.nativeapplication.entities.c.*;
import cz.mg.nativeapplication.entities.mg.components.MgClass;
import cz.mg.nativeapplication.entities.mg.components.MgStructure;
import cz.mg.nativeapplication.entities.mg.components.MgVariable;


public @Service class CStructureCreator { // todo - simplify
    private static final String CONSTRUCTOR_NAME = "create";
    private static final String DESTRUCTOR_NAME = "delete";
    private static final String MEMORY_MANAGER_PATH = "Mg/MgMemoryManager.h";

    public void create(CFolder cFolder, MgStructure mgStructure){
        List<CFile> files = new List<>();

        // ---------- ---------- ---------- ---------- ----------

        CFile structureDeclarationFile = new CFile();
        structureDeclarationFile.name = mgStructure.name + "_si.h";

        structureDeclarationFile.components.addLast(createGuardsBegin(structureDeclarationFile.name));
        structureDeclarationFile.components.addLast(new CSeparator());

        CStructureDeclaration structureDeclaration = new CStructureDeclaration();
        structureDeclaration.name = mgStructure.name;
        structureDeclarationFile.components.addLast(structureDeclaration);

        structureDeclarationFile.components.addLast(new CSeparator());
        structureDeclarationFile.components.addLast(createGuardsEnd());

        files.addLast(structureDeclarationFile);

        // ---------- ---------- ---------- ---------- ----------

        CFile structureDefinitionFile = new CFile();
        structureDefinitionFile.name = mgStructure.name + "_sd.h";

        structureDefinitionFile.components.addLast(createGuardsBegin(structureDefinitionFile.name));
        structureDefinitionFile.components.addLast(new CSeparator());

        CStructureDefinition structureDefinition = new CStructureDefinition();
        structureDefinition.declaration = structureDeclaration;
        for(MgVariable mgVariable : mgStructure.variables){
            CVariable cVariable = new CVariable();
            cVariable.name = mgVariable.name;
            cVariable.type = new CType();
            cVariable.type.structure = mgVariable.type instanceof MgStructure || mgVariable.type instanceof MgClass;
            cVariable.type.name = mgVariable.type.name;
            cVariable.type.pointers = mgVariable.pointers;
            structureDefinition.variables.addLast(cVariable);
        }
        structureDefinitionFile.components.addLast(structureDefinition);

        structureDefinitionFile.components.addLast(new CSeparator());
        structureDefinitionFile.components.addLast(createGuardsEnd());

        files.addLast(structureDefinitionFile);

        // ---------- ---------- ---------- ---------- ----------

        if(mgStructure.withMemoryManagement){
            CFile functionDeclarationFile = new CFile();
            functionDeclarationFile.name = mgStructure.name + "_fi.h";

            functionDeclarationFile.components.addLast(createGuardsBegin(functionDeclarationFile.name));
            functionDeclarationFile.components.addLast(new CSeparator());

            functionDeclarationFile.components.addLast(
                createInclude(structureDeclarationFile.name, true)
            );
            functionDeclarationFile.components.addLast(new CSeparator());

            CFunctionDeclaration createDeclaration = new CFunctionDeclaration();
            createDeclaration.name = mgStructure.name + "_" + CONSTRUCTOR_NAME;
            createDeclaration.output = new CType();
            createDeclaration.output.structure = true;
            createDeclaration.output.name = structureDeclaration.name;
            createDeclaration.output.pointers = 1;
            functionDeclarationFile.components.addLast(createDeclaration);

            CFunctionDeclaration deleteDeclaration = new CFunctionDeclaration();
            deleteDeclaration.name = mgStructure.name + "_" + DESTRUCTOR_NAME;
            CVariable input = new CVariable();
            input.name = "object";
            input.type = new CType();
            input.type.structure = true;
            input.type.name = mgStructure.name;
            input.type.pointers = 1;
            deleteDeclaration.input.addLast(input);
            functionDeclarationFile.components.addLast(deleteDeclaration);

            functionDeclarationFile.components.addLast(new CSeparator());
            functionDeclarationFile.components.addLast(createGuardsEnd());

            files.addLast(functionDeclarationFile);

            // ---------- ---------- ---------- ---------- ----------

            CFile functionDefinitionFile = new CFile();
            functionDefinitionFile.name = mgStructure.name + "_fd.c";
            functionDefinitionFile.components.addLast(
                createInclude(functionDeclarationFile.name, true)
            );
            functionDefinitionFile.components.addLast(new CSeparator());

            CFunctionDefinition createDefinition = new CFunctionDefinition();
            createDefinition.declaration = createDeclaration;
            //createDefinition.commands.addLast(todo);
            functionDefinitionFile.components.addLast(createDefinition);

            functionDefinitionFile.components.addLast(new CSeparator());

            CFunctionDefinition deleteDefinition = new CFunctionDefinition();
            deleteDefinition.declaration = deleteDeclaration;
            //deleteDefinition.commands.addLast(todo);
            functionDefinitionFile.components.addLast(deleteDefinition);

            files.addLast(functionDefinitionFile);
        }

        // ---------- ---------- ---------- ---------- ----------

        CFile mainHeaderFile = new CFile();
        mainHeaderFile.name = mgStructure.name + ".h";

        for(CFile file : files){
            mainHeaderFile.components.addLast(createInclude(file.name, true));
        }

        // ---------- ---------- ---------- ---------- ----------

        cFolder.files.addCollectionLast(files);
        cFolder.files.addLast(mainHeaderFile);
    }

    private CGuardsBegin createGuardsBegin(String filename){
        CGuardsBegin guardsBegin = new CGuardsBegin();
        guardsBegin.name = filename.replace('.', '_').toUpperCase();
        return guardsBegin;
    }

    private CInclude createInclude(String path, boolean local){
        CInclude include = new CInclude();
        include.path = path;
        include.local = local;
        return include;
    }

    private CGuardsEnd createGuardsEnd(){
        return new CGuardsEnd();
    }
}
