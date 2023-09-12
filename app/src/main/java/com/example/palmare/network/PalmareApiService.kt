package com.example.palmare.network

import android.provider.ContactsContract.Profile
import android.util.Log
import com.example.palmare.model.ItemModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


//Tutorial: https://johncodeos.com/how-to-make-post-get-put-and-delete-requests-with-retrofit-using-kotlin/
// authenticate: https://stackoverflow.com/questions/55666987/how-to-implement-oauth2-authorization-on-android
private const val BASE_URL = "http://turing.domain.eonegroup.it:8001/sap/bc/"
private const val USER = "eone"
private const val PASSWORD = "thebest"
private const val TAG = "APISERVICE"
private const val DATA = "zpalm_get_data"

interface PalmareApiService {
    //Define how to talk to the web server using HTTP requests.
    @POST(DATA)
    fun sendAuth(): Call<ResponseBody>
}

// authenticate with retrofit: https://www.javacodemonk.com/retrofit-basic-authentication-in-android-a47245fb#:~:text=You%20will%20have%20to%20create,by%20using%20its%20basic%20function.
class BasicAuthInterceptor(username: String, password: String, jsonData: MutableList<ItemModel>) : Interceptor {
    private var credentials: String = Credentials.basic(username, password)
    // Create JSON using JSONObject
    private val jsonObject = JSONObject()
    private val objData = jsonObject.put("items", jsonData)

    //Convert JSONObject to String
    private val jsonObjectString = objData.toString()

    //Solution for body: https://stackoverflow.com/questions/34791244/retrofit2-modifying-request-body-in-okhttp-interceptor
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        val body = request.body
        request = request.newBuilder().header("Authorization", credentials).post(
            (jsonObjectString).toRequestBody(
//                "application/json".toMediaTypeOrNull()
                body?.contentType()
            )
        )
            .build()
        Log.d(TAG, "request... $request")
        return chain.proceed(request)
    }
}

class BasicAuthClient<T>(jsonData: MutableList<ItemModel>) {
    private val client = OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor(USER, PASSWORD, jsonData))
        .build()

    val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun create(service: Class<T>): T {
        return retrofit.create(service)
    }
}

fun loadProfile(jsonData: MutableList<ItemModel>) {

    val call = BasicAuthClient<PalmareApiService>(jsonData)
        .create(PalmareApiService::class.java).sendAuth()

    call.enqueue(object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            Log.e(TAG, t.message, t)
        }

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {
                Log.i(
                    TAG,
                    "Profile Loaded. \nHeader: ${response.headers()} \ncode and message: ${response.code()}\n${response.message()}\nBody: ${response.body()}"
                )
                    Log.i(TAG, "full info: $response")

                // Convert raw JSON to pretty JSON using GSON library
                val gson = GsonBuilder().setPrettyPrinting().create()
                val prettyJson = gson.toJson(
                    JsonParser.parseString(
                        response.body()
                            ?.toString() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                    )
                )
                Log.d(TAG, "prettyjson: $prettyJson")
            } else {
                Log.e(
                    TAG,
                    "Error: ${response.code()} ${response.message()}\n headers: ${response.headers()} \n body ${response.body()} \n REQUEST: ${call.request()}"
                )
            }
        }
    })
}