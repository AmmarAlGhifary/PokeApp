package com.example.pokeapp.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }

fun <A> performGetRemoteOnly(
    networkCall: suspend () -> Resource<A>
): LiveData<Resource<A>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())


        val _result = MutableLiveData<A>()
        val result: LiveData<A> = _result

        val source = result.map { Resource.success(it) }
        emitSource(source)
        Log.d("source", "performGetRemoteOnly: " + source)
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            _result.postValue(responseStatus.data!!)
            Log.d("lol hayu", "performGetOperation: " + responseStatus)

        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }

fun <A> performGetLocalOnly(
    databaseQuery: () -> LiveData<A>
): LiveData<Resource<A>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)
        Log.d("localsource", "performGetLocalOnly: " + source)
    }
