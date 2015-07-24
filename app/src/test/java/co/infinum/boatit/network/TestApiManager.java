package co.infinum.boatit.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import android.util.Log;

import java.io.IOException;
import java.net.CookieManager;
import java.util.concurrent.Executor;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by kjurkovic on 11/07/15.
 */
public class TestApiManager implements ApiManager {

    private static final String TAG = "Network";

    public static final String API_ENDPOINT = "https://boatit.infinum.co";

    private Gson gson = new GsonBuilder().create();

    private static final RestAdapter.Log LOG = new RestAdapter.Log() {
        @Override
        public void log(String message) {
            Log.d(TAG, message);
        }
    };

    private static TestApiManager instance;

    private BoatItService service;

    private MockWebServer mockWebServer;

    public synchronized static TestApiManager getInstance() {
        if (instance == null) {
            instance = new TestApiManager();
        }
        return instance;
    }

    private TestApiManager() {
    }

    public void init() {
        OkHttpClient okHttpClient = new OkHttpClient().setCookieHandler(new CookieManager());
        setup(new SingleThreadExecutor(), new SingleThreadExecutor(), new OkClient(okHttpClient));
    }

    public void init(Executor httpExecutor, Executor callbackExecutor, OkClient okClient) {
        setup(httpExecutor, callbackExecutor, okClient);
    }

    private void setup(Executor httpExecutor, Executor callbackExecutor, OkClient okClient) {
        mockWebServer = new MockWebServer();

        try {
            mockWebServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setClient(okClient)
                .setEndpoint(mockWebServer.getUrl("/").toString())
                .setRequestInterceptor(new Interceptor())
                .setConverter(new GsonConverter(gson))
                .setLog(LOG)
                .setLogLevel(RestAdapter.LogLevel.FULL);

        if (httpExecutor != null && callbackExecutor != null) {
            builder.setExecutors(httpExecutor, callbackExecutor);
        }

        service = builder.build().create(BoatItService.class);
    }

    public BoatItService getService() {
        return service;
    }

    public MockWebServer getMockWebServer() {
        return mockWebServer;
    }
}
