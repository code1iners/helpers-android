package com.example.helpers

import android.content.Context
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.helpers.Status.HTTP_200_OK
import com.example.helpers.Status.RESPONSE_STATUS
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONArray
import org.json.JSONObject

object JsonManager {
    val TAG = JsonManager::class.simpleName
    
    fun jsonToJson(parameter: JSONObject): JsonObject {
        return JsonParser().parse(parameter.toString()) as JsonObject
    }
    
    fun stringToJson(parameter: String): JsonObject {
        return JsonParser().parse(parameter) as JsonObject
    }

    fun resultOnlyOk(): JsonObject {
        return jsonToJson(JSONObject().apply {
            this.put("status", HTTP_200_OK)
        })
    }
    
    class JsonRequester(context: Context?) {
        val TAG = JsonRequester::class.simpleName
        var queue = Volley.newRequestQueue(context)
        var requestJsonObject: JsonObjectRequest? = null
        var requestJsonArray: JsonArrayRequest? = null
        var setOnJsonRequestListener: JsonRequestManagerListener? = null
        
        fun requestJsonObject(url: String) {
            requestJsonObject = JsonObjectRequest(Request.Method.GET, url, null,
                    { r -> setOnJsonRequestListener?.isSuccess(r) },
                    { e -> setOnJsonRequestListener?.isFailure(e) }
            )
            
            requestJsonObject?.tag = TAG
            queue.add(requestJsonObject)
        }
        
        fun requestJsonArray(url: String) {
            requestJsonArray = JsonArrayRequest(Request.Method.GET, url, null,
                    { r -> setOnJsonRequestListener?.isSuccess(r) },
                    { e -> setOnJsonRequestListener?.isFailure(e) }
            )
            
            requestJsonArray?.tag = TAG
            queue.add(requestJsonArray)
        }
        
        fun terminate() {
            queue.cancelAll(requestJsonObject?.tag)
            queue.cancelAll(requestJsonArray?.tag)
        }
        
        interface JsonRequestManagerListener {
            fun isSuccess(response: JSONArray)
            fun isSuccess(response: JSONObject)
            fun isFailure(error: VolleyError)
        }
    }
}