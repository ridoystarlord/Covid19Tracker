package com.ridoy.covid19tracker.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoronaAPI {

    //private static Retrofit retrofit=null;

    public static ApiInterface getApi(){
        /*if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }*/
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
      return  retrofit.create(ApiInterface.class);
    }
    public static ApiInterface getCountryApi(){
        /*if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiInterface.IP_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }*/
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(ApiInterface.IP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit.create(ApiInterface.class);
    }
}
