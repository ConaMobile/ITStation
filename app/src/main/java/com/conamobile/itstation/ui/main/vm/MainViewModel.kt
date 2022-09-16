package com.conamobile.itstation.ui.main.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conamobile.itstation.ui.main.models.MockApiModel
import com.conamobile.itstation.ui.main.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {
    private val _itStationImages: MutableLiveData<List<MockApiModel>> =
        MutableLiveData(emptyList())
    val itStationImages: LiveData<List<MockApiModel>> get() = _itStationImages
    var isSuccessLoadImage = mutableStateOf(true)

    init {
        getITStationImages()
    }

    private fun getITStationImages() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.getITStationImages().isSuccessful) {
                isSuccessLoadImage.value = true
                _itStationImages.postValue(repository.getITStationImages().body())
            } else isSuccessLoadImage.value = false

        }
    }
}
