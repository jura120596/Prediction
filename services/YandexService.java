package com.example.prediction.services;

import com.example.prediction.YaResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YandexService {
    @GET("/api/v1/predict.json/complete")
    Call<YaResult> prediction(@Query("key") String key,
                              @Query("lang") String lang,
                              @Query("q") String text,
                              @Query("limit") int limit);
}
