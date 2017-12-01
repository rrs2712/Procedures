package com.touchsurgery.procedures.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.touchsurgery.procedures.R;
import com.touchsurgery.procedures.data.model.Procedure;
import com.touchsurgery.procedures.ui.detail.DetailActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String ITEM_ID = "item_detail_id";
    private final String DEV = "RRS";
    private final String TAG = DEV + ":" + this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrieveData();
    }

    /*private void setWidgets(Callback<Model> callback, final List<Procedure> procedures) {

        ProcedureAdapter procedureAdapter = new ProcedureAdapter(this,procedures);

        final ListView lv = (ListView) findViewById(R.id.listview_procedures);
        lv.setAdapter(procedureAdapter);
//        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Procedure procedure = procedures.get(position);
                Log.d(TAG,"Item: " +  procedure.toString());
                showDetail(procedure.getId());
            }
        });
    }*/

    private void setWidgets(Callback<List<Procedure>> callback, final List<Procedure> procedures) {

        ProcedureAdapter procedureAdapter = new ProcedureAdapter(this,procedures);

        final ListView lv = (ListView) findViewById(R.id.listview_procedures);
        lv.setAdapter(procedureAdapter);
//        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Procedure procedure = procedures.get(position);
                Log.d(TAG,"Item: " +  procedure.toString());
                showDetail(procedure.getId());
            }
        });
    }

    private void showDetail(String id) {
        Log.d(TAG,"Selected item: " + id);

        Bundle b = new Bundle();
        b.putString(ITEM_ID,id);

        Intent i = new Intent(this, DetailActivity.class);
        i.putExtras(b);
        startActivity(i);
    }

    private void retrieveData() {
        Log.d(TAG,"retrieveData");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProcedureContract.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProcedureContract procedureContract = retrofit.create(ProcedureContract.class);
        Log.d(TAG,"procedureContract created");

//        Call<Model> requestModel = procedureContract.getProcedures();
        Call<List<Procedure>> requestModel = procedureContract.getProcedures();
        Log.d(TAG,"requestProcedures created");

//        requestModel.enqueue(new Callback<Model>() {
        requestModel.enqueue(new Callback<List<Procedure>>() {
            @Override
//            public void onResponse(Call<Model> call, Response<Model> response) {
            public void onResponse(Call<List<Procedure>> call, Response<List<Procedure>> response) {
                Log.d(TAG, "onResponse");
                if(!response.isSuccessful()){
                    Log.d(TAG,"unsuccessful: " + response.code());
                    return;
                }

                Log.d(TAG, "Body has " + response.toString());
//                Model dataModel = response.body();
                List<Procedure> dataModel = response.body();

//                setWidgets(this,dataModel.procedures);
                setWidgets(this,dataModel);

//                for (Procedure procedure : dataModel.procedures){
                for (Procedure procedure : dataModel){
                    Log.d(TAG,String.format("%s: %s", procedure.getId(), procedure.getName()));
                    Log.d(TAG,"----------------");
                }
            }

            @Override
//            public void onFailure(Call<Model> call, Throwable t) {
            public void onFailure(Call<List<Procedure>> call, Throwable t) {
                Log.e(TAG,"onFailure: " + t.getMessage());
            }
        });
    }
}
