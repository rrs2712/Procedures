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
 *
 * Class to implementing {@link IDetail.Model} to retrieve data from an end point and
 * populate views with such data.
 *
 * @author Raul RS
 * @version 1.0
 */
public class DetailModel implements IDetail.Model {

    // MVP
    private IDetail.Presenter presenter;
    // Log
    private final String DEV = "RRS";
    private final String TAG = DEV + ":" + this.getClass().getSimpleName();

    /**
     * Class constructor. Receives a presenter to instantiate this class.
     * @param presenter - {{@link IDetail.Presenter}}
     */
    public DetailModel(IDetail.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     *
     * Fills views passed as references by parameters:
     *
     * @param iv - ImageView
     * @param tv - TextView
     * @param lv - ListView
     * @param context - Activity
     * @param procedureID - String
     */
    @Override
    public void fillWidgets(final ImageView iv, final TextView tv, final ListView lv, final Activity context, String procedureID) {
        retrieveData(iv, tv, lv, context, procedureID);
    }

    /**
     *
     * Obtains data from an end point using {@link Retrofit} and {@link com.google.gson.Gson}
     *
     * @param iv - ImageView
     * @param tv - TextView
     * @param lv - ListView
     * @param context - Activity
     * @param procedureID - String
     */
    private void retrieveData(final ImageView iv, final TextView tv, final ListView lv, final Activity context, String procedureID) {
        Log.d(TAG,"retrieveData");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServiceContract.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServiceContract detailContract = retrofit.create(ServiceContract.class);

        Call<Detail> requestDetail = detailContract.getDetail(procedureID);

        requestDetail.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Call<Detail> call, Response<Detail> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG,"unsuccessful: " + response.code());
                    return;
                }

                Detail detail = response.body();
                setWidgets(detail,iv,tv, lv, context);
            }

            @Override
            public void onFailure(Call<Detail> call, Throwable t) {
                Log.e(TAG,"onFailure: " + t.getMessage());
            }
        });
    }

    /**
     * Method to set a custom views of a detail object.
     *
     * @param detail - {@link Detail}
     * @param iv - ImageView
     * @param tv - TextView
     * @param lv - ListView
     * @param context - Activity
     */
    private void setWidgets(Detail detail, ImageView iv, TextView tv, ListView lv, Activity context) {
        ImageLoader imageloader = ImageLoader.getInstance();
        imageloader.init(ImageLoaderConfiguration.createDefault(context));
        imageloader.displayImage(detail.getCard(), iv);

        tv.setText(detail.getName());

        PhaseAdapter phaseAdapter = new PhaseAdapter(context,detail.getPhases());
        lv.setAdapter(phaseAdapter);
    }
}
