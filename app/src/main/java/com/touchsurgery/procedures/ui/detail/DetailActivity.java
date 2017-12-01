package com.touchsurgery.procedures.ui.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.touchsurgery.procedures.R;
import com.touchsurgery.procedures.data.model.Detail;
import com.touchsurgery.procedures.ui.main.MainActivity;
import com.touchsurgery.procedures.ui.main.ProcedureContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    private final String DEV = "RRS";
    private final String TAG = DEV + ":" + this.getClass().getSimpleName();
    private String procedureID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        retrieveBundle();
        retrieveData();
    }

    private void retrieveBundle(){
        Bundle bundle = getIntent().getExtras();
        procedureID = bundle.getString(MainActivity.ITEM_ID,"");

        if(procedureID.equals("")){
            Toast.makeText(this,"Empty procedure ID",Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }

    private void retrieveData() {
        Log.d(TAG,"retrieveData");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProcedureContract.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DetailContract detailContract = retrofit.create(DetailContract.class);
        Log.d(TAG,"procedureContract created");

//        Call<Model> requestModel = procedureContract.getProcedures();
        Call<Detail> requestDetail = detailContract.getDetail(procedureID);
        Log.d(TAG,"requestProcedures created");

//        requestModel.enqueue(new Callback<Model>() {
        requestDetail.enqueue(new Callback<Detail>() {
            @Override
//            public void onResponse(Call<Model> call, Response<Model> response) {
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                Log.d(TAG, "onResponse");
                if(!response.isSuccessful()){
                    Log.d(TAG,"unsuccessful: " + response.code());
                    return;
                }

                Log.d(TAG, "Body has " + response.toString());
//                Model dataModel = response.body();
                Detail detail = response.body();

                setWidgets(this,detail);

//                for (Procedure procedure : dataModel.procedures){
                Log.d(TAG,String.format("%s: %s", detail.getId(), detail.getName()));
                Log.d(TAG,String.format("%s", detail.getPhases().toString()));
                Log.d(TAG,"----------------");
            }

            @Override
//            public void onFailure(Call<Model> call, Throwable t) {
            public void onFailure(Call<Detail> call, Throwable t) {
                Log.e(TAG,"onFailure: " + t.getMessage());
            }
        });
    }

    private void setWidgets(Callback<Detail> callback, final Detail detail) {
        ImageView iv = (ImageView) findViewById(R.id.detail_icon);
        TextView tv = (TextView) findViewById(R.id.detail_name);
        final ListView lv = (ListView) findViewById(R.id.listview_detail);

        ImageLoader imageloader = ImageLoader.getInstance();
        imageloader.init(ImageLoaderConfiguration.createDefault(this));
        imageloader.displayImage(detail.getCard(), iv);
//        iv.setImageResource(R.drawable.download);

        tv.setText(detail.getName());

        PhaseAdapter phaseAdapter = new PhaseAdapter(this,detail.getPhases());
        lv.setAdapter(phaseAdapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Phase phase = detail.getPhases().get(position);
//                Log.d(TAG,"Phase: " +  phase.toString());
//            }
//        });
    }

   /* private void retrieveData() {
        Log.d(TAG,"retrieveData");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProcedureContract.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DetailContract detailContract = retrofit.create(DetailContract.class);
        Log.d(TAG,"detailContract created");

        Call<Model> requestModel = detailContract.getDetail(procedureID);
        Log.d(TAG,"requestProcedures created");

        requestModel.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Log.d(TAG, "onResponse");
                if(!response.isSuccessful()){
                    Log.d(TAG,"unsuccessful: " + response.code());
                    return;
                }

                Log.d(TAG, "Body has " + response.toString());
                Model dataModel = response.body();

//                setWidgets(this,dataModel.procedures);

                for (Detail detail : dataModel.details){
                    Log.d(TAG,String.format("%s: %s \n %s", detail.getId(), detail.getName(), detail.getPhases().toString()));
                    Log.d(TAG,"----------------");
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.e(TAG,"onFailure: " + t.getMessage());
            }
        });
    }*/
}
