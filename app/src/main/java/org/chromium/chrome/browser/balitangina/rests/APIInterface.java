package org.chromium.chrome.browser.balitangina.rests;

import org.chromium.chrome.browser.balitangina.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("top-headlines")
    Call<ResponseModel> getLatestNews(@Query("country") String country, @Query("apiKey") String apiKey);
}
