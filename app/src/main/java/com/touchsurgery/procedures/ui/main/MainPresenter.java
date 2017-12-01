package com.touchsurgery.procedures.ui.main;

import android.app.Activity;
import android.widget.ListView;

/**
 * Created by rrs27 on 2017-12-01.
 */

public class MainPresenter implements IProcedure.Presenter {

    private IProcedure.View view;
    private IProcedure.Model model;

    public MainPresenter(IProcedure.View view) {
        this.view = view;
        this.model = new MainModel(this);
    }

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
