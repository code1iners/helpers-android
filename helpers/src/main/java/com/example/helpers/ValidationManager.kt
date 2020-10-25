package com.example.helpers

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import com.example.helpers.Status.HTTP_400_BAD_REQUEST
import com.example.helpers.Status.RESPONSE_MESSAGE
import com.example.helpers.Status.RESPONSE_STATUS
import com.google.gson.JsonObject
import org.json.JSONObject
import java.util.*

object ValidationManager {
    val TAG = ValidationManager::class.simpleName
    var response: JsonObject? = null
    var listener = BooleanVariable()
    
    // note. responses
    const val UNIQUE_KEY_LENGTH = 33

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun usernameIsValid(username: String): Boolean {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        if (username.isEmpty()) {
            response = JsonManager.jsonToJson(JSONObject().apply {
                this.put(RESPONSE_STATUS, HTTP_400_BAD_REQUEST)
                this.put(RESPONSE_MESSAGE, "아이디를 입력해주세요.")
            })
            listener.isBoo = true
            return false
        } else {
            // note. check email type
            if (!isValidEmail(username)) {
                response = JsonManager.jsonToJson(JSONObject().apply {
                    this.put(RESPONSE_STATUS, HTTP_400_BAD_REQUEST)
                    this.put(RESPONSE_MESSAGE, "아이디가 이메일 형식이 아닙니다.")
                })
                listener.isBoo = true
                return false
            }
        }
        response = JsonManager.resultOnlyOk()
        listener.isBoo = true
        return true
    }

    fun passwordIsValid(password: String): Boolean {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        if (password.isEmpty()) {
            response = JsonManager.jsonToJson(JSONObject().apply {
                this.put(RESPONSE_STATUS, HTTP_400_BAD_REQUEST)
                this.put(RESPONSE_MESSAGE, "비밀번호를 입력해주세요.")
            })
            listener.isBoo = true
            return false
        } else if (password.length < 8 || password.length > UNIQUE_KEY_LENGTH) {
            response = JsonManager.jsonToJson(JSONObject().apply {
                this.put(RESPONSE_STATUS, HTTP_400_BAD_REQUEST)
                this.put(RESPONSE_MESSAGE, "비밀번호를 확인해주세요(8~30자리).")
            })
            listener.isBoo = true
            return false
        }
        response = JsonManager.resultOnlyOk()
        listener.isBoo = true
        return true
    }

    fun birthdateYearIsValid(birthdate_year: String): Boolean {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        val refYear = Calendar.getInstance().get(Calendar.YEAR)
        if (birthdate_year.isEmpty()) {
            response = JsonManager.jsonToJson(JSONObject().apply {
                this.put(RESPONSE_STATUS, HTTP_400_BAD_REQUEST)
                this.put(RESPONSE_MESSAGE, "생년을 확인해주세요.")
            })
            listener.isBoo = true
            return false
        } else {
            if (birthdate_year.toInt() > refYear) {
                response = JsonManager.jsonToJson(JSONObject().apply {
                    this.put(RESPONSE_STATUS, HTTP_400_BAD_REQUEST)
                    this.put(RESPONSE_MESSAGE, "생년을 확인해주세요.")
                })
                listener.isBoo = true
                return false
            } else if ((refYear - birthdate_year.toInt()) > 150) {
                response = JsonManager.jsonToJson(JSONObject().apply {
                    this.put(RESPONSE_STATUS, HTTP_400_BAD_REQUEST)
                    this.put(RESPONSE_MESSAGE, "생년을 확인해주세요.")
                })
                listener.isBoo = true
                return false
            }
        }
        response = JsonManager.resultOnlyOk()
        listener.isBoo = true
        return true
    }

    fun birthdateMonthIsValid(birthdate_month: String): Boolean {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        if (birthdate_month.isEmpty()) {
            response = JsonManager.jsonToJson(JSONObject().apply {
                this.put(RESPONSE_STATUS, HTTP_400_BAD_REQUEST)
                this.put(RESPONSE_MESSAGE, "생월을 확인해주세요.")
            })
            listener.isBoo = true
            return false
        } else {
            if (birthdate_month.toInt() > 12 || birthdate_month.toInt() == 0) {
                response = JsonManager.jsonToJson(JSONObject().apply {
                    this.put(RESPONSE_STATUS, HTTP_400_BAD_REQUEST)
                    this.put(RESPONSE_MESSAGE, "생월을 확인해주세요.")
                })
                listener.isBoo = true
                return false
            }
        }
        response = JsonManager.resultOnlyOk()
        listener.isBoo = true
        return true
    }

    fun birthdateDayIsValid(birthdate_day: String): Boolean {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        if (birthdate_day.isEmpty()) {
            response = JsonManager.jsonToJson(JSONObject().apply {
                this.put(RESPONSE_STATUS, HTTP_400_BAD_REQUEST)
                this.put(RESPONSE_MESSAGE, "생일을 확인해주세요.")
            })
            listener.isBoo = true
            return false
        } else {
            if (birthdate_day.toInt() > 31 || birthdate_day.toInt() == 0) {
                response = JsonManager.jsonToJson(JSONObject().apply {
                    this.put(RESPONSE_STATUS, HTTP_400_BAD_REQUEST)
                    this.put(RESPONSE_MESSAGE, "생일을 확인해주세요.")
                })
                listener.isBoo = true
                return false
            }
        }
        response = JsonManager.resultOnlyOk()
        listener.isBoo = true
        return true
    }

    fun genderIsValid(gender: String): Boolean {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        if (gender.isEmpty()) {
            response = JsonManager.jsonToJson(JSONObject().apply {
                this.put(RESPONSE_STATUS, HTTP_400_BAD_REQUEST)
                this.put(RESPONSE_MESSAGE, "성별을 입력해주세요.")
            })
            listener.isBoo = true
            return false
        }
        response = JsonManager.resultOnlyOk()
        listener.isBoo = true
        return true
    }
}