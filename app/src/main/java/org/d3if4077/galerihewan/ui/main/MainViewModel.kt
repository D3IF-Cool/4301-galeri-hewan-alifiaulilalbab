package org.d3if4077.galerihewan.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.d3if4077.galerihewan.R
import org.d3if4077.galerihewan.model.Hewan
import org.d3if4077.galerihewan.network.HewanApi

class MainViewModel : ViewModel()  {

    private val data = MutableLiveData<List<Hewan>>()
    private val status = MutableLiveData<HewanApi.ApiStatus>()

    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch {
            status.value = HewanApi.ApiStatus.LOADING
            try {
                data.value = HewanApi.service.getHewan()
                status.value = HewanApi.ApiStatus.FAILED
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message} ")
            }
        }
    }

    fun getData(): LiveData<List<Hewan>> = data
    fun getStatus() : LiveData<HewanApi.ApiStatus> = status
}