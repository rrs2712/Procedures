package com.touchsurgery.procedures.ui.detail;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by rrs27 on 2017-12-01.
 */

public class DetailPresenter implements IDetail.Presenter {
    private IDetail.View view;
    private IDetail.Model model;

    public  DetailPresenter(IDetail.View view){
        this.view = view;
        this.model = new DetailModel(this);
    }

    @Override
    public void showDetail(ImageView iv, TextView tv, ListView lv, Activity context, String procedureID) {
        if(view!=null){
            model.fillWidgets(iv, tv, lv, context, procedureID);
        }
    }
}
