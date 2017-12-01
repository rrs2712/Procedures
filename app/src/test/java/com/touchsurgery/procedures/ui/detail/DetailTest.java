package com.touchsurgery.procedures.ui.detail;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by rrs27 on 2017-12-01.
 */

public class DetailTest {

    private DetailView detailView;
    private DetailPresenter detailPresenter;

    /**
     * Check if {@link DetailView} is implementing {@link IDetail.View}
     */
    @Test
    public void testMainView(){
        detailView = new DetailView();
        assertTrue(detailView instanceof IDetail.View);
    }

    /**
     * Check if {@link DetailPresenter} is implementing {@link IDetail.Presenter}
     */
    @Test
    public void testMainPresenter(){
        detailPresenter = new DetailPresenter(detailView);
        assertTrue(detailPresenter instanceof IDetail.Presenter);
    }

    /**
     * Check if {@link DetailModel} is implementing {@link IDetail.Model}
     */
    @Test
    public void testMainModel(){
        DetailModel detailModel = new DetailModel(detailPresenter);
        assertTrue(detailModel instanceof IDetail.Model);
    }
}
