package com.touchsurgery.procedures.data.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rrs27 on 2017-11-30.
 */

public interface ServiceContract {


//    public static final String END_POINT="http://localhost:3000/";
    public static final String END_POINT="http://192.168.1.19:3000/";
//    public static final String END_POINT ="http://www.mocky.io/v2/5a2038c6310000b830c0b219/";

    @GET("procedures")
    Call<List<Procedure>> getProcedures();

    @GET("procedure_details/{id}")
    Call<Detail> getDetail(@Path(value = "id", encoded = true) String id);

    //Todo: create only 1 interface, rename it and move it to anther path
}
