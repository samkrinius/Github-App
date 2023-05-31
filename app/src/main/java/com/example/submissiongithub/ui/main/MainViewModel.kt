package com.example.submissiongithub.ui.main

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.*
import com.example.submissiongithub.api.RetrofitClient
import com.example.submissiongithub.data.local.SettingPreferences
import com.example.submissiongithub.data.model.User
import com.example.submissiongithub.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainViewModel (private val preferences: SettingPreferences): ViewModel() {
    val listUsers = MutableLiveData<ArrayList<User>>()
    fun getTheme() = preferences.getThemeSetting().asLiveData()

    fun setSearchUsers(query: String){
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }
    fun getSearchUsers(): LiveData<ArrayList<User>>{
        return listUsers
    }
    class Factory(private val preferences: SettingPreferences) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MainViewModel(preferences) as T
    }
}