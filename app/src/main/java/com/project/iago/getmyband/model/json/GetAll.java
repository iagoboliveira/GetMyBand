package com.project.iago.getmyband.model.json;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Iago Note on 05/12/2017.
 */

public interface GetAll {

  /*  @GET("response")
    Call<Response> all();*/
/*

    @GET("response")
    Call<JsonVagalume> getBands(
            @Query("search.art?q=") String artist,
            @Query("limit") Integer limit);
*/
/*

    @GET("response")
    Call<JsonVagalume> getBands(
            @Query("term=") String artist,
            @Query("limit") Integer limit);
*/


    @GET("results")
    Call<LastFM_JSON> getBands();

    @GET("{band}/Doc")
    Call<Doc> select(@Path("band") String name);
}
