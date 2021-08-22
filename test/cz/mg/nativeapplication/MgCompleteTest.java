package cz.mg.nativeapplication;

import cz.mg.nativeapplication.gui.Repositories;
import cz.mg.nativeapplication.c.services.creator.CProjectCreator;
import cz.mg.nativeapplication.c.services.exporter.CProjectExporter;
import cz.mg.nativeapplication.mg.services.storage.MgProjectLoader;
import cz.mg.nativeapplication.mg.services.storage.MgProjectSaver;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;

import java.nio.file.Path;
import java.nio.file.Paths;


public class MgCompleteTest implements Test {
    private static final String FILENAME = "Test.mg";
    public static final Path PATH = Paths.get(TempStorageSaver.PATH.toString(), FILENAME);

    public static void main(String[] args) {
        Repositories.init();
        new SingleTestRunner().run(new MgCompleteTest());
    }

    @TestCase
    public void test(){
        new MgProjectSaver().save(
            PATH, new MgTestProjectCreator().create()
        );

        new TempStorageSaver().save(
            new CProjectExporter().export(
                new CProjectCreator().create(
                    new MgProjectLoader().load(PATH)
                )
            )
        );
    }
}
