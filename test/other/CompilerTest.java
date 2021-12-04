package other;

import cz.mg.nativeapplication.c.services.creator.CProjectCreator;
import cz.mg.nativeapplication.c.services.exporter.CProjectExporter;
import cz.mg.nativeapplication.mg.services.storage.MgProjectLoader;
import cz.mg.nativeapplication.mg.services.storage.MgProjectSaver;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;

import static all.Configuration.PROJECT_FILE_PATH;


public class CompilerTest implements Test {
    public static void main(String[] args) {
        new SingleTestClassRunner().run(CompilerTest.class);
    }

    @TestCase
    public void test(){
        new MgProjectSaver().save(
            PROJECT_FILE_PATH, new TestProjectCreator().create()
        );

        new TempStorageSaver().save(
            new CProjectExporter().export(
                new CProjectCreator().create(
                    new MgProjectLoader().load(PROJECT_FILE_PATH)
                )
            )
        );
    }
}
