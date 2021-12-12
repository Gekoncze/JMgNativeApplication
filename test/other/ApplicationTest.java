package other;

import all.Preparation;
import cz.mg.nativeapplication.gui.Application;
import cz.mg.nativeapplication.gui.services.ApplicationService;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.cli.runners.SingleTestClassRunner;

import static all.Configuration.PROJECT_FILE_PATH;


public class ApplicationTest implements Test {
    public static void main(String[] args) {
        new Preparation().prepare();

        new SingleTestClassRunner().run(
            CompilerTest.class
        );

        System.out.println();

        new SingleTestClassRunner().run(
            ApplicationTest.class
        );
    }

    @TestCase
    public void test(){
        Application application = new Application();
        new ApplicationService().openProject(
            application.getExplorer(),
            PROJECT_FILE_PATH
        );
        application.getMainWindow().refresh();
        application.getMainWindow().setVisible(true);
    }
}
