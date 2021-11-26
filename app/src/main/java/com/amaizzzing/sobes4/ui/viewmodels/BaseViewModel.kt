package com.amaizzzing.sobes4.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T>: ViewModel() {
    private val _data = MutableLiveData<T?>()

    val data: LiveData<T?> = _data

    fun setData(newData: T) {
        _data.value = newData
    }
}