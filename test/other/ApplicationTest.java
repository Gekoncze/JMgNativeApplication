package other;

import cz.mg.nativeapplication.gui.Application;
import cz.mg.nativeapplication.gui.Repositories;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.SingleTestRunner;


public class ApplicationTest implements Test {
    public static void main(String[] args) {
        Repositories.init();
        new SingleTestRunner().run(
            new MgCompleteTest()
        );
        System.out.println();
        new SingleTestRunner().run(
            new ApplicationTest()
        );
    }

    @TestCase
    public void test(){
        Application application = new Application();
        application.getApplicationState().openProject(MgCompleteTest.PATH);
        application.getMainWindow().refresh();
        application.getMainWindow().setVisible(true);
    }
}
