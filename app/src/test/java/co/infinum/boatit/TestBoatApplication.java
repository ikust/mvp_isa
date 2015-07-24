package co.infinum.boatit;

import co.infinum.boatit.network.BoatItService;

/**
 * Created by ivan on 24/07/15.
 */
public class TestBoatApplication extends BoatApplication {

    private static TestBoatApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    public static TestBoatApplication getInstance() {
        return instance;
    }

    private void init() {

    }

    public static BoatItService getApiService() {
        return null;
    }
}
