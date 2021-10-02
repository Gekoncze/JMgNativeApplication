package other;

import cz.mg.nativeapplication.gui.Repositories;
import cz.mg.nativeapplication.gui.components.MainWindow;
import cz.mg.test.Test;
import cz.mg.test.annotations.TestCase;
import cz.mg.test.runner.BulkTestRunner;


public class MainWindowTest implements Test {
    public static void main(String[] args) {
        Repositories.init();
        new BulkTestRunner().run(
            new MgCompleteTest(),
            new MainWindowTest()
        );
    }

    @TestCase
    public void test(){
        MainWindow mainWindow = new MainWindow();
        mainWindow.getApplicationState().openProject(MgCompleteTest.PATH);
        mainWindow.refresh();
        mainWindow.setVisible(true);
    }
}
