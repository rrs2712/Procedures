package com.touchsurgery.procedures.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.touchsurgery.procedures.R;
import com.touchsurgery.procedures.ui.detail.DetailView;

/**
 *
 * Activity implementing {@link IProcedure.View} to preserve MVP pattern.
 *
 * @author Raul RS
 * @version 1.0
 */
public class MainView extends AppCompatActivity implements IProcedure.View {

    // Log
    private final String DEV = "RRS";
    private final String TAG = DEV + ":" + this.getClass().getSimpleName();
    // Bundles
    public static final String ITEM_ID = "item_detail_id";
    // MVP
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

    /**
     *
     * Method called when users clicks on an item.
     *
     * @param id - String
     */
    private void showDetail(String id) {
        Log.d(TAG,"Selected item: " + id);

        Bundle b = new Bundle();
        b.putString(ITEM_ID,id);

        Intent i = new Intent(this, DetailView.class);
        i.putExtras(b);
        startActivity(i);
    }
}
