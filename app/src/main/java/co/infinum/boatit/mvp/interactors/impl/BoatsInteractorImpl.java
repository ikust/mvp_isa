package co.infinum.boatit.mvp.interactors.impl;

import co.infinum.boatit.mvp.interactors.BoatsInteractor;
import co.infinum.boatit.mvp.listeners.BoatsListener;

/**
 * Created by kjurkovic on 16/07/15.
 */
public class BoatsInteractorImpl implements BoatsInteractor {

    private BoatsListener boatsListener;

    @Override
    public void getBoats(BoatsListener listener) {
        boatsListener = listener;
        // api call
        // on api done: listener.onBoatsReceived(list);
        // ili listener.onError
    }

}
