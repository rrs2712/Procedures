package com.touchsurgery.procedures.ui.detail;

import com.touchsurgery.procedures.data.model.Detail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rrs27 on 2017-11-30.
 */

public interface DetailContract {

    @GET("procedure_details/{id}")
//    Call<Model> getDetail(@Path(value = "id", encoded = true) String id);
    Call<Detail> getDetail(@Path(value = "id", encoded = true) String id);
}
