package com.touchsurgery.procedures.ui.main;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.touchsurgery.procedures.data.model.Procedure;
import com.touchsurgery.procedures.data.model.ServiceContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rrs27 on 2017-12-01.
 */

public class MainModel implements IProcedure.Model{

    private final String DEV = "RRS";
    private final String TAG = DEV + ":" + this.getClass().getSimpleName();

    private IProcedure.Presenter presenter;

    public MainModel(IProcedure.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void fillList(ListView lv, Activity context) {
        retrieveData(lv, context);
    }

    private void retrieveData(final ListView lv, final Activity context) {
        Log.d(TAG,"retrieveData");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceContract.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceContract serviceContract = retrofit.create(ServiceContract.class);
        Log.d(TAG,"serviceContract created");

        Call<List<Procedure>> requestModel = serviceContract.getProcedures();
        Log.d(TAG,"requestProcedures created");

        requestModel.enqueue(new Callback<List<Procedure>>() {
            @Override
            public void onResponse(Call<List<Procedure>> call, Response<List<Procedure>> response) {
                Log.d(TAG, "onResponse");
                if(!response.isSuccessful()){
                    Log.d(TAG,"unsuccessful: " + response.code());
                    return;
                }

                Log.d(TAG, "Body has " + response.toString());
                List<Procedure> dataModel = response.body();

                setWidgets(this,dataModel, lv, context);

                for (Procedure procedure : dataModel){
                    Log.d(TAG,String.format("%s: %s", procedure.getId(), procedure.getName()));
                    Log.d(TAG,"----------------");
                }
            }

            @Override
            public void onFailure(Call<List<Procedure>> call, Throwable t) {
                Log.e(TAG,"onFailure: " + t.getMessage());
            }
        });
    }

    private void setWidgets(Callback<List<Procedure>> callback, final List<Procedure> procedures, ListView lv, Activity context) {

        ProcedureAdapter procedureAdapter = new ProcedureAdapter(context,procedures);

//        final ListView lv = (ListView) findViewById(R.id.listview_procedures);
        lv.setAdapter(procedureAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Procedure procedure = procedures.get(position);
                Log.d(TAG,"Item: " +  procedure.toString());
                presenter.sendDetailID(procedure.getId());
//                showDetail(procedure.getId());
            }
        });
    }

//    private void showDetail(String id) {
//        Log.d(TAG,"Selected item: " + id);
//
//        Bundle b = new Bundle();
//        b.putString(MainView.ITEM_ID,id);
//
//        Intent i = new Intent(this, DetailView.class);
//        i.putExtras(b);
//        startActivity(i);
//    }
}
