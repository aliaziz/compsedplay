package com.example.weekendchat.data.datasource

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weekendchat.data.ChatDB
import com.example.weekendchat.di.scope.ActivityScope
import javax.inject.Inject
import kotlin.random.Random

@ActivityScope
class LoginDataSource @Inject constructor(
    val chatDB: ChatDB,
    private val sharedPreferences: SharedPreferences
) {
    fun login(): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        data.postValue(true) //Post value vs set value
        // is post value is used if live data value needs to be changed on a worker thread.
        val random = Random.nextInt(100, 999)
        sharedPreferences.edit().putInt("code", random).commit()
        return data
    }

    fun verify(code: String): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        result.value = code.toInt() == sharedPreferences.getInt("code", 0)
        return result
    }
}