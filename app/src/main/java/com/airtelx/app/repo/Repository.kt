package com.airtelx.app.repo

import com.airtelx.app.data.models.SearchResultResponseModel
import com.airtelx.app.data.remote.AirtelAPIService
import com.airtelx.app.data.remote.Resource
import com.airtelx.app.data.remote.ResponseHandler
import com.airtelx.app.data.remote.RetrofitGenerator

open class Repository() {

    private val api: AirtelAPIService = RetrofitGenerator.createService(
        AirtelAPIService::class.java
    )
    private val responseHandler: ResponseHandler = ResponseHandler()

    val CONTENT_TYPE = "application/json"


    suspend fun getMatchingAddress(
        queryString: String,
        city: String
    ): Resource<SearchResultResponseModel> {

        return try {
            val response = api.getMatchingAddress(CONTENT_TYPE, queryString, city)

            responseHandler.handleSuccess(response)

        } catch (e: Exception) {
            //just extra logging
            responseHandler.handleException(e)
        }

    }


}