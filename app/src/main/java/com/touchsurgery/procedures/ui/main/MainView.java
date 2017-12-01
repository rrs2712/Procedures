package com.touchsurgery.procedures.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.touchsurgery.procedures.R;
import com.touchsurgery.procedures.ui.detail.DetailView;

public class MainView extends AppCompatActivity implements IProcedure.View {

    public static final String ITEM_ID = "item_detail_id";
    private final String DEV = "RRS";
    private final String TAG = DEV + ":" + this.getClass().getSimpleName();
    private IProcedure.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView) findViewById(R.id.listview_procedures);
        presenter = new MainPresenter(this);
        presenter.showProcedures(lv, this);
    }

    @Override
    public void showProcedureDetail(String detailID) {
        showDetail(detailID);
    }

    private void showDetail(String id) {
        Log.d(TAG,"Selected item: " + id);

        Bundle b = new Bundle();
        b.putString(ITEM_ID,id);

        Intent i = new Intent(this, DetailView.class);
        i.putExtras(b);
        startActivity(i);
    }
}
