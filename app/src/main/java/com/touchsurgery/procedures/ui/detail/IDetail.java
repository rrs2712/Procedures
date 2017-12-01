package com.touchsurgery.procedures.ui.detail;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by rrs27 on 2017-12-01.
 */

public interface IDetail {

    interface View{}

    interface Presenter{
        void showDetail(ImageView iv, TextView tv, ListView lv, Activity context, String procedureID);
    }

    interface Model{
        void fillWidgets(final ImageView iv, final TextView tv, final ListView lv, final Activity context, String procedureID);
    }
}
