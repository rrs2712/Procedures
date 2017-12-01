package com.touchsurgery.procedures.ui.detail;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.touchsurgery.procedures.data.model.Detail;
import com.touchsurgery.procedures.data.model.ServiceContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rrs27 on 2017-12-01.
 */

public class DetailModel implements IDetail.Model {

    private IDetail.Presenter presenter;
    private final String DEV = "RRS";
    private final String TAG = DEV + ":" + this.getClass().getSimpleName();

    public DetailModel(IDetail.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void fillWidgets(final ImageView iv, final TextView tv, final ListView lv, final Activity context, String procedureID) {
        retrieveData(iv, tv, lv, context, procedureID);
    }

    private void retrieveData(final ImageView iv, final TextView tv, final ListView lv, final Activity context, String procedureID) {
        Log.d(TAG,"retrieveData");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceContract.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceContract detailContract = retrofit.create(ServiceContract.class);
        Log.d(TAG,"procedureContract created");

        Call<Detail> requestDetail = detailContract.getDetail(procedureID);
        Log.d(TAG,"requestProcedures created");

        requestDetail.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                Log.d(TAG, "onResponse");
                if(!response.isSuccessful()){
                    Log.d(TAG,"unsuccessful: " + response.code());
                    return;
                }

                Log.d(TAG, "Body has " + response.toString());
                Detail detail = response.body();

                setWidgets(this,detail,iv,tv, lv, context);

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

    private void setWidgets(Callback<Detail> callback, Detail detail, ImageView iv, TextView tv, ListView lv, Activity context) {
//        ImageView iv = (ImageView) findViewById(R.id.detail_icon);
//        TextView tv = (TextView) findViewById(R.id.detail_name);
//        final ListView lv = (ListView) findViewById(R.id.listview_detail);

        ImageLoader imageloader = ImageLoader.getInstance();
        imageloader.init(ImageLoaderConfiguration.createDefault(context));
        imageloader.displayImage(detail.getCard(), iv);

        tv.setText(detail.getName());

        PhaseAdapter phaseAdapter = new PhaseAdapter(context,detail.getPhases());
        lv.setAdapter(phaseAdapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View View, int position, long id) {
//                Phase phase = detail.getPhases().get(position);
//                Log.d(TAG,"Phase: " +  phase.toString());
//            }
//        });
    }
}
