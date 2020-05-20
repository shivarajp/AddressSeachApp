package com.airtelx.app.data.remote

import com.airtelx.app.data.models.SearchResultResponseModel
import retrofit2.http.*


interface AirtelAPIService {

    @Headers("Accept: application/json")
    @GET("autocomplete")
    suspend fun getMatchingAddress(
        @Header("Content-Type") contentType: String,
        @Query("queryString") queryString : String,
        @Query("city") city : String
    ): SearchResultResponseModel

}