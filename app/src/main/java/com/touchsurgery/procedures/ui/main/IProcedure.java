package com.touchsurgery.procedures.ui.main;

import android.app.Activity;
import android.widget.ListView;

/**
 * Created by rrs27 on 2017-12-01.
 */

public interface IProcedure {

    interface View {
        void showProcedureDetail(String detailID);
    }

    interface Presenter{
        void showProcedures(ListView lv, Activity context);
        void sendDetailID(String detailID);
    }

    interface Model{
        void fillList(ListView lv, Activity context);
    }

}
