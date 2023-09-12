package com.example.palmare.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.palmare.model.ItemModel
import com.example.palmare.network.loadProfile
import kotlinx.coroutines.launch
const val TAG = "ViewModel"
class ArticleViewModel : ViewModel() {
    val items = mutableListOf<ItemModel>()

    //    var opType: Int = 0
    var scanQty = 0
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    fun addToList(
        typeId: Int,
        typeCommessa: String,
        item: String,
        quantity: String
    ) {
        val item = ItemModel(
            typeId, //operazione: op
            typeCommessa, // commessa: com
            item, //materiale: mat
            quantity //quantità : qty
        )
        items.add(item)
        scanQty = scanQty.inc()
    }


    fun operationComplete() {
        items.clear()
        scanQty = 0
    }

    fun sendAuth() {
        viewModelScope.launch {
            try {
//                rawJSON(items)
                loadProfile(items)
                _status.value = "Success: ${loadProfile(items)}}"
                Log.d("ViewModel", "Success status: ${status}")
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
                Log.d(TAG, "Error authenticate message: ${status}, ${e.message}")
            }

        }
    }
}