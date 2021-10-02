package cz.mg.nativeapplication;

import cz.mg.nativeapplication.gui.Repositories;
import cz.mg.nativeapplication.gui.services.ComponentSearchTest;
import cz.mg.nativeapplication.mg.services.explorer.DeleteServiceTest;
import cz.mg.nativeapplication.mg.services.explorer.SearchServiceTest;
import cz.mg.nativeapplication.mg.services.history.HistoryTest;
import cz.mg.nativeapplication.mg.services.other.CollectionTypeProviderTest;
import cz.mg.test.runner.BulkTestRunner;


public class AllTests {
    public static void main(String[] args) {
        Repositories.init();

        new BulkTestRunner().run(
            // gui.services
            new ComponentSearchTest(),

            // mg.services.explorer
            new DeleteServiceTest(),
            new SearchServiceTest(),

            // mg.services.history
            new HistoryTest(),

            // mg.services.other
            new CollectionTypeProviderTest()
        );
    }
}
