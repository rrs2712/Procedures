package com.touchsurgery.procedures.ui.main;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by rrs27 on 2017-12-01.
 */

public class MainTest {

    private MainView mainView;
    private MainPresenter mainPresenter;

    /**
     * Check if {@link MainView} is implementing {@link IProcedure.View}
     */
    @Test
    public void testMainView(){
        mainView= new MainView();
        assertTrue(mainView instanceof IProcedure.View);
    }

    /**
     * Check if {@link MainPresenter} is implementing {@link IProcedure.Presenter}
     */
    @Test
    public void testMainPresenter(){
        mainPresenter = new MainPresenter(mainView);
        assertTrue(mainPresenter instanceof IProcedure.Presenter);
    }

    /**
     * Check if {@link MainModel} is implementing {@link IProcedure.Model}
     */
    @Test
    public void testMainModel(){
        MainModel mainModel = new MainModel(mainPresenter);
        assertTrue(mainModel instanceof IProcedure.Model);
    }
}
