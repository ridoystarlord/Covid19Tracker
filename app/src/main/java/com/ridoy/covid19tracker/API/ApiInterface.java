package com.ridoy.covid19tracker.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    static final String BASE_URL="https://corona.lmao.ninja/v2/";
    static final String IP_BASE_URL="http://ip-api.com/";

    @GET("countries")
    Call<List<CountryData>> getCountryData();

    @GET("json")
    Call<UserCountry> getIpCountryData();
}
