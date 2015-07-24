package co.infinum.boatit;

import android.app.Application;

import co.infinum.boatit.network.ApiManager;
import co.infinum.boatit.network.ApiManagerImpl;
import co.infinum.boatit.network.BoatItService;

/**
 * Created by kjurkovic on 16/07/15.
 */
public class BoatApplication extends Application {

    private static BoatApplication instance;

    protected ApiManager apiManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    public static BoatApplication getInstance() {
        return instance;
    }

    protected void init() {
        ApiManagerImpl.getInstance().init();

        apiManager = ApiManagerImpl.getInstance();
    }

    public static BoatItService getApiService() {
        return getInstance().apiManager.getService();
    }
}
