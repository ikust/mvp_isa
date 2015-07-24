package co.infinum.boatit;

import co.infinum.boatit.network.TestApiManager;

/**
 * Created by ivan on 24/07/15.
 */
public class TestBoatApplication extends BoatApplication {

    protected void init() {

        //Initialize mock API manager.

        TestApiManager.getInstance().init();

        apiManager = TestApiManager.getInstance();
    }

}
