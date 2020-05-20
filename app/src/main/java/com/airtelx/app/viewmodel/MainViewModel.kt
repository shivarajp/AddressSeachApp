package com.airtelx.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.airtelx.app.data.models.SearchResultResponseModel
import com.airtelx.app.data.remote.Resource
import com.airtelx.app.repo.Repository
import kotlinx.coroutines.Dispatchers

class MainViewModel(val dataRepository: Repository): ViewModel() {

    fun getMatchingAddress(queryString: String, city: String): LiveData<Resource<SearchResultResponseModel>> {

        return liveData(Dispatchers.IO) {

            emit(Resource.loading(null))
            val data = dataRepository.getMatchingAddress(queryString, city)
            emit(data)

        }
    }
}