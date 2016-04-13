package com.example.admin.w3d2retrofittest;

import android.util.Log;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by admin on 4/12/2016.
 */
public class RetrofitTest {

    public static void main(String... args){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.duckduckgo.com/").addConverterFactory(GsonConverterFactory.create()).build();

        DuckService duckService = retrofit.create(DuckService.class);
        Call<Result> listCall = duckService().listCharacters("simpsons characters");

        Result result = null;

        try {
            result = listCall.execute().body();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return (result != null) ? result.getRelatedTopics() : null;
    }

    public interface DuckService{
        @GET("?format=json")
        Call<Result> listCharacters(@Query("q") String q);
    }
}
