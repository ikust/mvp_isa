package co.infinum.boatit.network;

import java.util.concurrent.Executor;

/**
 * Created by ivan on 24/07/15.
 */
public class SingleThreadExecutor implements Executor {

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
