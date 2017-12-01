package com.touchsurgery.procedures.data.model;

import com.touchsurgery.procedures.ui.main.ProcedureContract;

import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rrs27 on 2017-11-30.
 */

public class ModelTest {

    private final String TAG = this.getClass().getSimpleName();

    @Test
    public void ProcedureClient(){
        System.out.println("ProcedureClient");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProcedureContract.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProcedureContract procedureContract = retrofit.create(ProcedureContract.class);
        Call<List<Procedure>> requestProcedures = procedureContract.getProcedures();
        requestProcedures.enqueue(new Callback<List<Procedure>>() {
            @Override
            public void onResponse(Call<List<Procedure>> call, Response<List<Procedure>> response) {
                System.out.println("onResponse");
                if(!response.isSuccessful()){
//                    Log.d(TAG,"unsuccessful: " + response.code());
                    System.out.println("unsuccessful: " + response.code());
                    return;
                }

                List<Procedure> Procedures = response.body();

                for (Procedure procedure : Procedures){
                    System.out.println(String.format("%s: %s", procedure.getId(), procedure.getName()));
//                    Log.d(TAG,String.format("%s: %s", procedure.getId(), procedure.getName()));
//                    Log.d(TAG,"----------------");
                }
            }

            @Override
            public void onFailure(Call<List<Procedure>> call, Throwable t) {
                System.out.println("onFailure");
//                Log.e(TAG,t.getMessage());
            }
        });
    }
}
