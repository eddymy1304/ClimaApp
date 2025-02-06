package com.eddymy1304.climaapp.core

import android.util.Log
import com.eddymy1304.climaapp.core.Result.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.Response

suspend fun <T : Any> handleResult(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    timeout: Long = 5000,
    block: suspend () -> Response<T>
): Result<T> {

    return try {
        withContext(dispatcher) {

            val response = withTimeout(timeout) {
                block()
            }

            Log.d("Response API", response.body().toString())

            if (response.isSuccessful && response.body() != null) {
                Success(response.body()!!)
            } else {
                Error(Exception(response.message()))
            }
        }
    } catch (e: Exception) {
        Error(e)
    }

}