package co.infinum.boatit.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.squareup.okhttp.OkHttpClient;

import android.util.Log;

import java.net.CookieManager;
import java.util.concurrent.Executor;

import co.infinum.boatit.enums.UserAuthorized;
import co.infinum.boatit.network.deserializers.UserAuthorizedDeserializer;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by kjurkovic on 11/07/15.
 */
public class ApiManagerImpl implements ApiManager {

    private static final String TAG = "Network";

    public static final String API_ENDPOINT = "https://boatit.infinum.co";

    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(UserAuthorized.class, new UserAuthorizedDeserializer())
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();

    private static final RestAdapter.Log LOG = new RestAdapter.Log() {
        @Override
        public void log(String message) {
            Log.d(TAG, message);
        }
    };

    private static ApiManagerImpl instance;

    private BoatItService service;


    public synchronized static ApiManagerImpl getInstance() {
        if (instance == null) {
            instance = new ApiManagerImpl();
        }
        return instance;
    }

    private ApiManagerImpl() {
    }

    public void init() {
        OkHttpClient okHttpClient = new OkHttpClient().setCookieHandler(new CookieManager());
        setup(null, null, new OkClient(okHttpClient));
    }

    public void init(Executor httpExecutor, Executor callbackExecutor, OkClient okClient) {
        setup(httpExecutor, callbackExecutor, okClient);
    }

    private void setup(Executor httpExecutor, Executor callbackExecutor, OkClient okClient) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setClient(okClient)
                .setEndpoint(API_ENDPOINT)
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

}
