package co.infinum.boatit.mvp.presenters.impl;

import co.infinum.boatit.models.Boat;
import co.infinum.boatit.mvp.interactors.BoatsInteractor;
import co.infinum.boatit.mvp.presenters.BoatsPresenter;
import co.infinum.boatit.mvp.views.BoatsView;

/**
 * Created by kjurkovic on 16/07/15.
 */
public class BoatsPresenterImpl implements BoatsPresenter {

    private BoatsView view;

    private BoatsInteractor interactor;

    public BoatsPresenterImpl(BoatsView view) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getBoats() {

    }

    @Override
    public void onBoatClicked(Boat boat) {

    }
}
