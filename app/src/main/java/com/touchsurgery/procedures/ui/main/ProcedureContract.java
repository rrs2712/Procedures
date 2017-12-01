package com.touchsurgery.procedures.ui.main;

import com.touchsurgery.procedures.data.model.Procedure;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rrs27 on 2017-11-30.
 */

public interface ProcedureContract {


//    public static final String END_POINT="http://localhost:3000/";
    public static final String END_POINT="http://192.168.1.19:3000/";
//    public static final String END_POINT ="http://www.mocky.io/v2/5a2038c6310000b830c0b219/";

    @GET("procedures")
//    Call<Model> getProcedures();
    Call<List<Procedure>> getProcedures();
}
