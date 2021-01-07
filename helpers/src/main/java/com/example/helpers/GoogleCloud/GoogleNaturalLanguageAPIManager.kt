package com.example.helpers.GoogleCloud

import android.util.Log
import com.example.helpers.JsonManager
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GoogleNaturalLanguageAPIManager {
    companion object {
        val TAG = GoogleNaturalLanguageAPIManager::class.simpleName
    }

    var naturalLanguageListener: NaturalLanguageListener? = null

    fun analyzeSentiment(key: String, content: String) {
        try {
            GoogleNaturalLanguageAPI()
                .analyzeSentiment(key, JsonManager.jsonToJson(
                    JSONObject(
                        JSONObject()
                            .put("encodingType", "UTF8")
                            .put("document", JSONObject().apply {
                                this.put("type", "PLAIN_TEXT")
                                this.put("content", content)
                            }).toString()
                    )
                ))
                .enqueue(object: Callback<JsonObject> {
                    override fun onResponse(call: Call<JsonObject>, r: Response<JsonObject>) {
                        Log.v(TAG, "${r.raw()}")
                        when (r.isSuccessful) {
                            true -> { Log.i(TAG, "analyzeSentiment(SUCCESS)") }
                            false -> { Log.w(TAG, "analyzeSentiment(WARNING)") }
                        }
                        naturalLanguageListener?.analyzeSentimentIsDone(r.code(), r.body())
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Log.e(TAG, "analyzeSentiment(FAILURE):${t.message}")
                    }
                })
        } catch (e: Exception) {e.printStackTrace()}
    }

    interface NaturalLanguageListener {
        fun analyzeSentimentIsDone(code: Int, results: JsonObject?)
    }
}