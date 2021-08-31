package com.example.ctf.repository

import android.app.Application
import com.example.ctf.data.local.entities.*
import com.example.ctf.data.remote.CTFApi
import com.example.ctf.data.remote.requests.AccountRequest
import com.example.ctf.data.remote.requests.ListStringRequest
import com.example.ctf.data.remote.requests.OneRequest
import com.example.ctf.data.remote.requests.UpdateUserRequest
import com.example.ctf.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CTFRepository @Inject constructor(
    private val ctfApi: CTFApi,
    private val context: Application
){
    suspend fun login(email: String, password: String) = withContext(Dispatchers.IO) {
        try {
            val response = ctfApi.login(AccountRequest(email, password))
            if (response.isSuccessful && response.body()!!.successful) {
                Resource.success(response.body()?.message)
            } else {
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        } catch (e: Exception) {
            Resource.error("Couldnt connect to the server", null)
        }
    }

    suspend fun register(email: String, password: String) = withContext(Dispatchers.IO) {
        try {
            val response = ctfApi.register(AccountRequest(email, password))
            if (response.isSuccessful && response.body()!!.successful) {
                Resource.success(response.body()?.message)
            } else {
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        } catch (e: Exception) {
            Resource.error("Couldnt connect to the server", null)
        }
    }
    suspend fun getUser(username: String)= withContext(Dispatchers.IO){
        try {
            val response= ctfApi.getUser(OneRequest(property = username))
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server", null)
        }
    }
    suspend fun updateProfile(updateUserReq: UpdateUserRequest) = withContext(Dispatchers.IO) {
        try {
            val response = ctfApi.updateProfile(updateUserReq)
            if (response.isSuccessful && response.body()!!.successful) {
                Resource.success(response.body()!!.message)
            } else {
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        } catch (e: Exception) {
            Resource.error("Couldnt connect to the server", null)
        }
    }

    suspend fun saveWall(wall: Wall) = withContext(Dispatchers.IO){
        try{
            val response = ctfApi.saveWall(wall)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getWall(username: String)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.getWall(OneRequest(username))
            if(response.isSuccessful){
                Resource.success(response.body())
            }else {
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun deleteWall(wall: Wall)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.deleteWall(wall)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun saveChat(chat: Chat)= withContext(Dispatchers.IO){
        try {
            val response = ctfApi.saveChat(chat)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getChat()= withContext(Dispatchers.IO){
        try {
            val response= ctfApi.getChat()
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch(e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun saveTrading(trading: Trading)= withContext(Dispatchers.IO) {
        try {
            val response = ctfApi.saveTrading(trading)
            if (response.isSuccessful && response.body()!!.successful) {
                Resource.success(response.body()!!.message)
            } else {
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        } catch (e: Exception) {
            Resource.error("Couldnt connect to the server", null)
        }
    }
    suspend fun deleteTrading(trading: Trading)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.deleteTrading(trading)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getAllTrading()= withContext(Dispatchers.IO){
        try {
            val response= ctfApi.getAllTrading()
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch(e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getAllUserTrading(query:String)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.getAllUserTrading(OneRequest(query))
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getTrading(trading: Trading)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.getTrading(trading)
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getBuyingSearch(query:String)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.getBuyingSearch(OneRequest(query))
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getSellingSearch(query:String)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.getSellingSearch(OneRequest(query))
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getTitleSearch(query: String)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.getTitleSearch(OneRequest(query))
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun saveParty(party: Party)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.saveParty( party)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getPartyList()= withContext(Dispatchers.IO){
        try {
            val response= ctfApi.getPartyList()
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch(e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun saveDrop(dropped: Dropped)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.saveDrop( dropped)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getDropList()= withContext(Dispatchers.IO){
        try {
            val response= ctfApi.getDrop()
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch(e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun saveToday(today: Today)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.saveToday(today)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(), null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getToday()= withContext(Dispatchers.IO){
        try {
            val response= ctfApi.getToday()
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch(e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun deleteDrop(dropped: Dropped)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.deleteDrop(dropped)
            if(response.isSuccessful && response.body()!!.successful){
                Resource.success(response.body()!!.message)
            }else{
                Resource.error(response.body()?.message ?: response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }  }
    suspend fun deletion()= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.deletion()
            if(response.isSuccessful){
                Resource.success(response.body()?.message)
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun toggleCheck(party: Party)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.toggleCheck(party)
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun toggleDrop(party: Party)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.toggleDrop(party)
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun toggleNope(party: Party)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.toggleNope(party)
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getListUser(listString:List<String>)= withContext(Dispatchers.IO){
        try{
            val response=ctfApi.getListUser(ListStringRequest(listString))
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch(e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getListUserClub(club:String)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.ListUserClub(OneRequest(club))
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }
    suspend fun getListUserIgn(ign:String)= withContext(Dispatchers.IO){
        try {
            val response=ctfApi.ListUserIgn(OneRequest(ign))
            if(response.isSuccessful){
                Resource.success(response.body())
            }else{
                Resource.error(response.message(),null)
            }
        }catch (e:Exception){
            Resource.error("Couldnt connect to the server",null)
        }
    }

}