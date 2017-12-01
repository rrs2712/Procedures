package com.touchsurgery.procedures.data.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interface to manage async calls to back end using Retrofit 2 notation
 *
 * @author Raul RS
 * @version 1.0
 */
public interface ServiceContract {


    public static final String END_POINT="http://localhost:3000/";
//    public static final String END_POINT="http://192.168.1.19:3000/";

    /**
     * Method to implement a solution focused on retrieve a list of {@link Procedure}
     *
     * @return Procedures - Call<List<Procedure>>
     */
    @GET("procedures")
    Call<List<Procedure>> getProcedures();

    /**
     * Method to implement a solution focused on retrieve an specific {@link Detail} object
     * by id
     *
     * @return Detail - Call<Detail>
     */
    @GET("procedure_details/{id}")
    Call<Detail> getDetail(@Path(value = "id", encoded = true) String id);
}
