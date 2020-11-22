package com.example.helpers

import android.app.Activity
import android.util.Log
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.rx
import com.kakao.sdk.user.UserApiClient
import com.kakao.sdk.user.model.User
import com.kakao.sdk.user.rx
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class KakaoUtilities {

    var listener: KakaoListener? = null

    fun loginWithKakaoAccount(activity: Activity, disposable: CompositeDisposable) {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        try {
            LoginClient.rx.loginWithKakaoAccount(activity)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ token ->
                    Log.i(TAG, "로그인 성공 ${token.accessToken}")

                }, { error ->
                    Log.e(TAG, "로그인 실패", error)

                })
                .addTo(disposable)
        } catch (e: Exception) {e.printStackTrace()}
    }

    fun loginWithKakaoTalk(activity: Activity, disposable: CompositeDisposable) {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        try {
            LoginClient.rx.loginWithKakaoTalk(activity)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ token ->
                  Log.i(TAG, "로그인 성공 ${token.accessToken}")

                }, { error ->
                  Log.e(TAG, "로그인 실패", error)

                })
                .addTo(disposable)
        } catch (e: Exception) {e.printStackTrace()}
    }

    fun login(activity: Activity, disposable: CompositeDisposable) {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        try {
          Single.just(LoginClient.instance.isKakaoTalkLoginAvailable(activity))
            .flatMap { available ->
              if (available) LoginClient.rx.loginWithKakaoTalk(activity)
              else LoginClient.rx.loginWithKakaoAccount(activity)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ token ->
              Log.i(TAG, "로그인 성공 ${token.accessToken}")

                listener?.loginIsDone(token.accessToken)

            }, { error ->
              Log.e(TAG, "로그인 실패", error)

            })
            .addTo(disposable)
        } catch (e: Exception) {e.printStackTrace()}
    }

    fun logout(disposable: CompositeDisposable) {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        try {
          UserApiClient.rx.logout()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제 됨")

            }, { error ->
                Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제 됨", error)

            }).addTo(disposable)
        } catch (e: Exception) {e.printStackTrace()}
    }

    fun unlink(disposable: CompositeDisposable) {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        try {
            UserApiClient.rx.unlink()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.i(TAG, "연결 끊기 성공. SDK에서 토큰 삭제 됨")

                }, { error ->
                    Log.e(TAG, "연결 끊기 실패", error)

                }).addTo(disposable)
        } catch (e: Exception) {e.printStackTrace()}
    }

    fun accessTokenInfo(disposable: CompositeDisposable) {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        try {
            UserApiClient.rx.accessTokenInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ tokenInfo ->
                    Log.i(
                        TAG, "토큰 정보 보기 성공" +
                          "\n회원번호: ${tokenInfo.id}" +
                          "\n만료시간: ${tokenInfo.expiresIn} 초")

                }, { error ->
                  Log.e(TAG, "토큰 정보 보기 실패", error)

                })
                .addTo(disposable)
        } catch (e: Exception) {e.printStackTrace()}
    }

    fun readMe(disposable: CompositeDisposable) {
        Log.w(TAG, object:Any(){}.javaClass.enclosingMethod!!.name)
        try {
            UserApiClient.rx.me()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ user ->
                  Log.i(TAG, "사용자 정보 요청 성공\nuser:$user")

                    listener?.readMeIsDone(user)

                }, { error ->
                    Log.e(TAG, "사용자 정보 요청 실패", error)

                })
                .addTo(disposable)
        } catch (e: Exception) {e.printStackTrace()}
    }

    interface KakaoListener{
        fun loginIsDone(accessToken: String)
        fun readMeIsDone(user: User)
    }

    companion object {
        val TAG = KakaoUtilities::class.simpleName
    }
}