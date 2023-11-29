package com.github.gunin_igor75.crypto_app.data.network.okHttp

import android.app.ActivityManager.RunningTaskInfo
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

open class BaseOkHttpSource(
    private val config: OkHttpConfig
) {
    private val contentType = "application/json; charset=utf-8".toMediaType()

    val gson = config.gson
    val client = config.client

    suspend fun Call.suspendEnqueue(): Response {
        return suspendCancellableCoroutine { continuation ->
            continuation.invokeOnCancellation {
                cancel()
            }
            enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    continuation.resumeWithException(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        continuation.resume(response)
                    } else {
                        handleErrorResponse(response, continuation)
                    }
                }
            })
        }
    }

    fun Request.Builder.endpoint(endpoint: String): Request.Builder {
        url("${config.baseUrl}$endpoint")
        return this
    }

    private fun handleErrorResponse(
        response: Response,
        continuation: CancellableContinuation<Response>
    ) {
        val httpCode = response.code
        try {
            val map = gson.fromJson(response.body?.string() ?: "", Map::class.java)
            val message = map["error"].toString()
            continuation.resumeWithException(RuntimeException("$httpCode  $message"))
        } catch (e: Exception) {
            continuation.resumeWithException(RuntimeException("Oops!"))
        }
    }

    fun <T> T.toJsonRequestBody(): RequestBody {
        val json = gson.toJson(this)
        return json.toRequestBody(contentType)
    }

    fun <T> Response.parseJsonResponse(typeToken: TypeToken<T>): T {
        try {
            return gson.fromJson(this.body?.string() ?: "", typeToken.type)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    inline fun <reified T> Response.parseJsonResponse(): T {
        try {
            return gson.fromJson(this.body?.string() ?: "", T::class.java)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}