package com.aves.practicaltest.repository

import com.aves.practicaltest.retrofit.ApiInterface
import com.aves.practicaltest.sealed.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FirstRepository(val mainApi: ApiInterface) {

    private var _responseType = MutableStateFlow<NetworkResult>(NetworkResult.Empty)
    var responseType = _responseType.asStateFlow()

    suspend fun getAllUser()
    {
        _responseType.value = NetworkResult.Loading
        try {
            val response = mainApi.getAllUser("8634366274bd23efb9b023fb9b2c6502e67f7dd5d6a7962b3b49fbee170940f8")
            if (response.code() == 200) {
                val userList = response.body()
                _responseType.value = NetworkResult.Success(userList)
            } else {
                _responseType.value = NetworkResult.Error<String>(response.message())
            }
        } catch (e: Exception) {
            _responseType.value = NetworkResult.Error<String>(e.message?:"")
        }
    }

}