package com.deepak.indianews;

import com.deepak.indianews.api.details.BusinessNews;
import com.deepak.indianews.api.details.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsDetails {
    @GET("top-headlines")
    Call<Headlines> getHeadline
            (@Query("country")String country,
             @Query("apiKey")String key);

    @GET("top-headlines")
    Call<BusinessNews> getBusiness(
            @Query("country")String country,
            @Query("category")String category,
            @Query("apiKey")String key);


}
