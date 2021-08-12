package com.example.ctf.ui.auth

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctf.data.remote.BasicAuthInterceptor
import com.example.ctf.repository.CTFRepository
import com.example.ctf.util.Constants.KEY_LOGGED_IN_PASSWORD
import com.example.ctf.util.Constants.KEY_LOGGED_IN_USERNAME
import com.example.ctf.util.Constants.LOGIN
import com.example.ctf.util.Constants.LOGOUT
import com.example.ctf.util.Constants.NOTIMER
import com.example.ctf.util.Constants.NO_PASSWORD
import com.example.ctf.util.Constants.NO_USERNAME
import com.example.ctf.util.Constants.TIMERKEYPREF
import com.example.ctf.util.Event
import com.example.ctf.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor (
    private val repository: CTFRepository
): ViewModel() {
    @Inject
    lateinit var sharedPref: SharedPreferences

    @Inject
    lateinit var basicAuthInterceptor: BasicAuthInterceptor

    var usernamevm: String? = null
    var passwordvm: String? = null


    private val _registerStatus = MutableLiveData<Event<Resource<String>>>()
    val registerStatus: LiveData<Event<Resource<String>>> = _registerStatus
    private val _loginStatus = MutableLiveData<Event<Resource<String>>>()
    val loginStatus: LiveData<Event<Resource<String>>> = _loginStatus

    fun loginUser(username:String,password:String){
        _loginStatus.postValue(Event(Resource.loading(null)))
        if(username.isEmpty() || password.isEmpty()){
            _loginStatus.postValue(Event(Resource.error("Please fill out all the fields",null)))
            return
        }
        viewModelScope.launch{
            usernamevm=username
            passwordvm=password
            val result= repository.login(username,password)
            _loginStatus.postValue(Event(result))
        }
    }

    fun registerUser(username:String,password:String,repeatedPassword:String){
        _registerStatus.postValue(Event(Resource.loading(null)))
        if(username.isEmpty() || password.isEmpty() || repeatedPassword.isEmpty()){
            _registerStatus.postValue(Event(Resource.error("Please fill out all the fields",null)))
            return
        }
        if(password != repeatedPassword){
            _registerStatus.postValue(Event(Resource.error("The passwords do not match", null)))
            return
        }
        if(username.length < 3 || password.length < 3){
            _registerStatus.postValue(Event(Resource.error("must be at least 3 characters.",null)))
            return
        }
        if(username.length > 24 || password.length > 24){
            _registerStatus.postValue(Event(Resource.error("characters are too long.",null)))
            return
        }
        viewModelScope.launch {
            val result = repository.register(username, password)
            _registerStatus.postValue(Event(result))
        }
    }
    fun timerLeft():Int{
        val timerpref=sharedPref.getString(TIMERKEYPREF, NOTIMER) ?: NOTIMER
        val timervm=if((timerpref.toLong() - System.currentTimeMillis()) /1000L  <= 0) 0L else (timerpref.toLong() - System.currentTimeMillis()) /1000L
        return timervm.toInt()
    }

    fun isLoggedIn():Boolean{
        usernamevm=sharedPref.getString(KEY_LOGGED_IN_USERNAME,NO_USERNAME) ?: NO_USERNAME
        passwordvm=sharedPref.getString(KEY_LOGGED_IN_PASSWORD, NO_PASSWORD) ?: NO_PASSWORD
        return usernamevm!= NO_USERNAME && passwordvm != NO_PASSWORD
    }
    fun authenticateApi(username:String, password: String){
        basicAuthInterceptor.username=username
        basicAuthInterceptor.password=password
    }

    private val _desc= MutableStateFlow("")
    var desc: StateFlow<String> = _desc
    fun getDesc()=viewModelScope.launch{
        isLoggedIn()
        val username =if(sharedPref.getString(KEY_LOGGED_IN_USERNAME,NO_USERNAME) == NO_USERNAME) LOGIN else LOGOUT
        _desc.value=username
    }
}

















