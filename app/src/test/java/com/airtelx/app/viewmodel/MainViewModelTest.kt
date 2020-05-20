package com.airtelx.app.viewmodel

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.airtelx.app.data.models.AddressList
import com.airtelx.app.data.models.Data
import com.airtelx.app.data.models.SearchResultResponseModel
import com.airtelx.app.data.remote.AirtelAPIService
import com.airtelx.app.data.remote.Resource
import com.airtelx.app.data.remote.ResponseHandler
import com.airtelx.app.data.remote.Status
import com.airtelx.app.repo.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.net.SocketException

class MainViewModelTest {


    @Mock
    lateinit var repository: Repository
    @Mock
    lateinit var  api: AirtelAPIService
    @Mock
    lateinit var responseHandler :ResponseHandler
    @Mock
    lateinit var socketException: SocketException
    lateinit var mainViewModel: MainViewModel

    @Mock
    lateinit var searchResultResponseModel: SearchResultResponseModel
    lateinit var  failed : Resource<SearchResultResponseModel>
    lateinit var  success : Resource<SearchResultResponseModel>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        mainViewModel = MainViewModel(repository)

        CoroutineScope(Dispatchers.IO).launch{
            mockData()
        }
    }


    @Test
    fun get_matching_address_failed_test() {

        with(mainViewModel){
            getMatchingAddress("test", "")
            assertEquals(failed.status, Status.ERROR)
        }
    }


    @Test
    fun get_matching_address_success_test() {

        with(mainViewModel){
            getMatchingAddress("airtel", "")
            assertEquals(success.status, Status.SUCCESS)
        }
    }

    suspend fun mockData() {
        failed = responseHandler.handleException(socketException)
        success = responseHandler.handleSuccess(searchResultResponseModel)
        `when`<SearchResultResponseModel>(api.getMatchingAddress("application/json","airtel", "")).thenReturn(searchResultResponseModel)
        `when`<Resource<SearchResultResponseModel>>(repository.getMatchingAddress("airtel", "")).thenReturn(failed)
        `when`<Resource<SearchResultResponseModel>>(repository.getMatchingAddress("airtel", "")).thenReturn(success)

    }




}