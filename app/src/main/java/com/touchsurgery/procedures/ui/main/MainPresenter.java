package com.touchsurgery.procedures.ui.main;

import android.app.Activity;
import android.widget.ListView;

/**
 *
 * Class to implementing {@link IProcedure.Presenter} to preserve MVP pattern.
 *
 * @author Raul RS
 * @version 1.0
 */
public class MainPresenter implements IProcedure.Presenter {

    // MVP variables
    private IProcedure.View view;
    private IProcedure.Model model;

    /**
     * Class constructor.
     *
     * @param view - {@link IProcedure.View}
     */
    public MainPresenter(IProcedure.View view) {
        this.view = view;
        this.model = new MainModel(this);
    }

    // ## Implementations ## //

    @Override
    public void showProcedures(ListView lv, Activity context) {
        if(view!=null){
            model.fillList(lv,context);
        }
    }

    @Override
    public void sendDetailID(String detailID) {
        if(view!=null){
            view.showProcedureDetail(detailID);
        }
    }
}
