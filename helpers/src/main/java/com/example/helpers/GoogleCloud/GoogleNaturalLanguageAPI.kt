package com.example.helpers.GoogleCloud

import com.example.helpers.BuildConfig
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface GoogleNaturalLanguageAPI {
	// note. sentiment
	@Headers("Content-type: application/json")
	@POST("./v1/documents:analyzeSentiment")
	fun analyzeSentiment(
			@Query("key") key: String,
			@Body obj: JsonObject
	): Call<JsonObject>
	
	companion object {
		val BASE_URL = "https://language.googleapis.com/"
		
		private fun setClient(): OkHttpClient {
			val loggingInterceptor = HttpLoggingInterceptor()
			loggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
			
			return OkHttpClient.Builder()
					.retryOnConnectionFailure(true)
					.addInterceptor(loggingInterceptor)
					.build()
		}
		
		operator fun invoke(): GoogleNaturalLanguageAPI {
			return Retrofit.Builder()
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.client(setClient())
					.build()
					.create(GoogleNaturalLanguageAPI::class.java)
		}
	}
}