package com.task.utilites;


import com.task.Model.SongModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by vishal on 10/18/2018.
 */

public interface Api {

    String BASE_URL = "https://itunes.apple.com/search?term=Michael+jackson";

    @GET("api-get-state")
    Call<SongModel> getCategories();

//    @FormUrlEncoded
//    @POST("api-get-state")
//    public void insertUser(
//            @Field("app_key") String name,
//            Callback<Response> callback);
//
//    @POST("api-get-state/{app_key}")
//    Call<CategoryModel> registration(@Path("app_key") String email);

}
