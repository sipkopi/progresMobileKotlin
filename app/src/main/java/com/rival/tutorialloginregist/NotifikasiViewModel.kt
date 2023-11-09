package com.rival.tutorialloginregist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotifikasiViewModel : ViewModel() {
    private val _daftarNotifikasi = MutableLiveData<MutableList<ItemNotifikasi>>()
    val daftarNotifikasi: LiveData<MutableList<ItemNotifikasi>>
        get() = _daftarNotifikasi

    init {
        _daftarNotifikasi.value = mutableListOf()
    }

    fun addNotification(notifikasi: ItemNotifikasi) {
        _daftarNotifikasi.value?.add(notifikasi)
        _daftarNotifikasi.postValue(_daftarNotifikasi.value)
    }
}
