package com.touchsurgery.procedures.ui.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.touchsurgery.procedures.R;
import com.touchsurgery.procedures.ui.main.MainView;

public class DetailView extends AppCompatActivity implements IDetail.View{

    private final String DEV = "RRS";
    private final String TAG = DEV + ":" + this.getClass().getSimpleName();
    private String procedureID;
    private IDetail.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        retrieveBundle();

        ImageView iv = (ImageView) findViewById(R.id.detail_icon);
        TextView tv = (TextView) findViewById(R.id.detail_name);
        ListView lv = (ListView) findViewById(R.id.listview_detail);

        presenter = new DetailPresenter(this);
        presenter.showDetail(iv,tv,lv, this, procedureID);
    }

    private void retrieveBundle(){
        Bundle bundle = getIntent().getExtras();
        procedureID = bundle.getString(MainView.ITEM_ID,"");

        if(procedureID.equals("")){
            Toast.makeText(this,"Empty procedure ID",Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

}
