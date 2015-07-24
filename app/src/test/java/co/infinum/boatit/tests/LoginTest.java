package co.infinum.boatit.tests;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.util.ActivityController;

import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import co.infinum.boatit.R;
import co.infinum.boatit.TestBoatApplication;
import co.infinum.boatit.activities.LoginActivity;
import co.infinum.boatit.activities.MainActivity;
import co.infinum.boatit.network.TestApiManager;
import co.infinum.boatit.utils.ResourceUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by ivan on 24/07/15.
 */
@Config(sdk = 21, application = TestBoatApplication.class)
@RunWith(RobolectricTestRunner.class)
public class LoginTest {

    private MockWebServer mockWebServer;

    @Before
    public void setUp() {
        mockWebServer = TestApiManager.getInstance().getMockWebServer();

        //Redirect logcat output to System.out so it shows in test reports.
        ShadowLog.stream = System.out;
    }

    @After
    public void tearDown() {
        try {
            //Shutdown MockWebServer gracefully.
            TestApiManager.getInstance().getMockWebServer().shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSuccessfullLogin() throws InterruptedException {
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setBody(ResourceUtils.readFromFile("login.json"))
        );

        ActivityController<LoginActivity> loginActivityController = Robolectric.buildActivity(LoginActivity.class);

        LoginActivity activity = loginActivityController.create()
                .start()
                .resume()
                .visible()
                .get();

        EditText usernameEditText = (EditText) activity.findViewById(R.id.username);
        EditText passwordEditText = (EditText) activity.findViewById(R.id.password);
        Button loginButton = (Button) activity.findViewById(R.id.login);

        usernameEditText.setText("admin@infinum.hr");
        passwordEditText.setText("infinum1");
        loginButton.performClick();

        RecordedRequest request = mockWebServer.takeRequest();
        assertThat(request.getHeader("Content-Type"), equalTo("application/json; charset=UTF-8"));

        assertThat(Shadows.shadowOf(loginActivityController.get()).getNextStartedActivity().getComponent().getClassName(),
                equalTo(MainActivity.class.getName()));
    }

}
