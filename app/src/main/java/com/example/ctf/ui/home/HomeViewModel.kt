package com.example.ctf.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ctf.data.local.entities.*
import com.example.ctf.repository.CTFRepository
import com.example.ctf.util.Event
import com.example.ctf.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CTFRepository
): ViewModel(){
    private val _saveChatStatus = MutableLiveData<Event<Resource<String>>>()
    val saveChatStatus : LiveData<Event<Resource<String>>> = _saveChatStatus
    private val _getChatStatus = MutableLiveData<Resource<List<Chat>>>()
    val getChatStatus : LiveData<Resource<List<Chat>>> = _getChatStatus
    private val _savePartyStatus = MutableLiveData<Event<Resource<String>>>()
    val savePartyStatus : LiveData<Event<Resource<String>>> = _savePartyStatus
    private val _partyListStatus = MutableLiveData<Resource<List<Party>>>()
    val partyListStatus : LiveData<Resource<List<Party>>> = _partyListStatus
    private val _saveDropStatus = MutableLiveData<Event<Resource<String>>>()
    val saveDropStatus : LiveData<Event<Resource<String>>> = _saveDropStatus
    private val _dropListStatus = MutableLiveData<Resource<List<Dropped>>>()
    val dropListStatus : LiveData<Resource<List<Dropped>>> = _dropListStatus
    private val _saveTodayStatus = MutableLiveData<Event<Resource<String>>>()
    val saveTodayStatus : LiveData<Event<Resource<String>>> = _saveTodayStatus
    private val _todayStatus = MutableLiveData<Resource<Today>>()
    val todayStatus : LiveData<Resource<Today>> = _todayStatus
    private val _deleteDropStatus = MutableLiveData<Event<Resource<String>>>()
    val deleteDropStatus : LiveData<Event<Resource<String>>> = _deleteDropStatus
    private val _listUserStatus = MutableLiveData<Resource<List<User>>>()
    val listUserStatus : LiveData<Resource<List<User>>> = _listUserStatus

    fun getListUser(listString:List<String>){
        _listUserStatus.postValue(Resource.loading(null))
        viewModelScope.launch {
            val result = repository.getListUser(listString)
            _listUserStatus.postValue(result)
        }
    }
    fun saveChat(chat: Chat){
        _saveChatStatus.postValue(Event(Resource.loading(null)))
        viewModelScope.launch {
            val result=repository.saveChat(chat)
            _saveChatStatus.postValue(Event(result))
            getChat()
        }
    }
    fun getChat(){
        _getChatStatus.postValue(Resource.loading(null))
        viewModelScope.launch {
            val result = repository.getChat()
            _getChatStatus.postValue(result)
        }
    }
    fun saveParty(query: String,party: Party){
        _savePartyStatus.postValue(Event(Resource.loading(null)))
        viewModelScope.launch {
            val result = repository.saveParty(party)
            _savePartyStatus.postValue(Event(result))
            getPartyList(query)
        }
    }
    fun getPartyList(query:String){
        _partyListStatus.postValue(Resource.loading(null))
        viewModelScope.launch {
            val result = repository.getPartyList(query)
            _partyListStatus.postValue(result)
        }
    }
    fun saveDrop(dropped: Dropped){
        _saveDropStatus.postValue(Event(Resource.loading(null)))
        viewModelScope.launch {
            val result = repository.saveDrop(dropped)
            _saveDropStatus.postValue(Event(result))
        }
    }
    fun getDropList(){
        _dropListStatus.postValue(Resource.loading(null))
        viewModelScope.launch {
            val result = repository.getDropList()
            _dropListStatus.postValue(result)
        }
    }
    fun saveToday(today: Today){
        _saveTodayStatus.postValue(Event(Resource.loading(null)))
        viewModelScope.launch {
            val result = repository.saveToday(today)
            _saveTodayStatus.postValue(Event(result))
        }
    }
    fun getToday(){
        _todayStatus.postValue(Resource.loading(null))
        viewModelScope.launch {
            val result = repository.getToday()
            _todayStatus.postValue(result)
        }
    }
    fun deleteDrop(dropped: Dropped){
        _deleteDropStatus.postValue(Event(Resource.loading(null)))
        viewModelScope.launch {
            val result = repository.deleteDrop(dropped)
            _deleteDropStatus.postValue(Event(result))
        }
    }
    fun toggleCheck(query: String,party: Party)=viewModelScope.launch {
        repository.toggleCheck(party)
        getPartyList(query)
    }

    fun toggleDrop(query: String,party: Party)= viewModelScope.launch {
        repository.toggleDrop(party)
        getPartyList(query)
    }

    fun toggleNope(query:String,party: Party)=viewModelScope.launch {
        repository.toggleNope(party)
        getPartyList(query)
    }

}