package com.touchsurgery.procedures.ui.detail;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 *
 * Class to implementing {@link IDetail.Presenter} to preserve MVP pattern.
 *
 * @author Raul RS
 * @version 1.0
 */
public class DetailPresenter implements IDetail.Presenter {
    private IDetail.View view;
    private IDetail.Model model;

    /**
     * Class constructor.
     *
     * @param view - {@link IDetail.View}
     */
    public  DetailPresenter(IDetail.View view){
        this.view = view;
        this.model = new DetailModel(this);
    }

    // ## Implementations ## //

    @Override
    public void showDetail(ImageView iv, TextView tv, ListView lv, Activity context, String procedureID) {
        if(view!=null){
            model.fillWidgets(iv, tv, lv, context, procedureID);
        }
    }
}
