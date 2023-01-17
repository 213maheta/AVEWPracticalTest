package com.aves.practicaltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aves.practicaltest.model.UserModel
import com.aves.practicaltest.repository.FirstRepository
import com.aves.practicaltest.sealed.MainEvent
import com.aves.practicaltest.sealed.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FirstViewModel(val repository: FirstRepository): ViewModel() {

    private var _event = MutableStateFlow<MainEvent>(MainEvent.Idle)
    var event = _event.asStateFlow()

    fun getAllUser() = viewModelScope.launch {
        repository.getAllUser()
    }

    fun setObserver() = viewModelScope.launch {
        repository.responseType.collect{
            when(it)
            {
                NetworkResult.Loading->{
                    _event.value = MainEvent.ShowProgressBar
                }
                is NetworkResult.Success<*>->{
                    _event.value = MainEvent.ResponseList(it.data as List<UserModel>)
                }
                is NetworkResult.Error<*>->{
                    _event.value = MainEvent.ShowToast<String>(it.message)
                }
                else -> {}
            }
        }
    }

}