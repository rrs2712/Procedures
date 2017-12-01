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
 *
 * Class to implementing {@link IProcedure.Model} to retrieve data from an end point and
 * populate a list view with such data.
 *
 * @author Raul RS
 * @version 1.0
 */
public class MainModel implements IProcedure.Model{

    // Log
    private final String DEV = "RRS";
    private final String TAG = DEV + ":" + this.getClass().getSimpleName();
    // Presenter
    private IProcedure.Presenter presenter;

    /**
     * Class constructor. Receives {@link IProcedure.Presenter} to instantiate this class.
     * @param presenter - IProcedure.Presenter
     */
    public MainModel(IProcedure.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Fills a list view with data obtained from an end point
     *
     * @param lv - {@link ListView}
     * @param context - {@link Activity}
     */
    @Override
    public void fillList(ListView lv, Activity context) {
        retrieveData(lv, context);
    }

    /**
     * Obtains data from an end point using {@link Retrofit} and {@link com.google.gson.Gson}
     *
     * @param lv - {@link ListView}
     * @param context - {@link Activity}
     */
    private void retrieveData(final ListView lv, final Activity context) {
        Log.d(TAG,"retrieveData");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceContract.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceContract serviceContract = retrofit.create(ServiceContract.class);
        Call<List<Procedure>> requestModel = serviceContract.getProcedures();

        requestModel.enqueue(new Callback<List<Procedure>>() {
            @Override
            public void onResponse(Call<List<Procedure>> call, Response<List<Procedure>> response) {
                if(!response.isSuccessful()){
                    Log.w(TAG,"unsuccessful: " + response.code());
                    return;
                }

                List<Procedure> dataModel = response.body();
                setWidgets(dataModel, lv, context);
            }

            @Override
            public void onFailure(Call<List<Procedure>> call, Throwable t) {
                Log.e(TAG,"onFailure: " + t.getMessage());
            }
        });
    }

    /**
     *
     * Method to set a custom ListView for a list of {@link Procedure}
     *
     * @param procedures - {@link List<Procedure>}
     * @param lv - {@link ListView}
     * @param context - {@link Activity}
     */
    private void setWidgets(final List<Procedure> procedures, ListView lv, Activity context) {

        ProcedureAdapter procedureAdapter = new ProcedureAdapter(context,procedures);

        lv.setAdapter(procedureAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Procedure procedure = procedures.get(position);
                Log.d(TAG,"Procedure: " +  procedure.toString());
                presenter.sendDetailID(procedure.getId());
            }
        });
    }

}
