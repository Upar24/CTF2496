package com.example.ctf.data.remote

import com.example.ctf.data.local.entities.*
import com.example.ctf.data.remote.requests.AccountRequest
import com.example.ctf.data.remote.requests.ListStringRequest
import com.example.ctf.data.remote.requests.OneRequest
import com.example.ctf.data.remote.requests.UpdateUserRequest
import com.example.ctf.data.remote.responses.SimpleResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CTFApi {

    @POST("/register")
    suspend fun register(
        @Body registerRequest: AccountRequest
    ): Response<SimpleResponse>

    @POST("/login")
    suspend fun login(
        @Body loginRequest: AccountRequest
    ):Response<SimpleResponse>

    @POST("/getuser")
    suspend fun getUser(
        @Body oneRequest: OneRequest
    ):Response<User>

    @POST("updateuser")
    suspend fun updateProfile(
        @Body updateUserReq: UpdateUserRequest
    ):Response<SimpleResponse>

    @POST("/savewall")
    suspend fun saveWall(
        @Body wall: Wall
    ):Response<SimpleResponse>

    @POST("/getwall")
    suspend fun getWall(
        @Body getRequest : OneRequest
    ):Response<List<Wall>>

    @POST("/deletewall")
    suspend fun deleteWall(
        @Body wall:Wall
    ):Response<SimpleResponse>

    @POST("/savechat")
    suspend fun saveChat(
        @Body chat: Chat
    ):Response<SimpleResponse>

    @GET("/getchat")
    suspend fun getChat():Response<List<Chat>>

    @POST("/savetrading")
    suspend fun saveTrading(
        @Body trading: Trading
    ):Response<SimpleResponse>

    @POST("/deletetrading")
    suspend fun deleteTrading(
        @Body trading:Trading
    ):Response<SimpleResponse>

    @GET("/getalltrading")
    suspend fun getAllTrading():Response<List<Trading>>

    @POST("/getallusertrading")
    suspend fun getAllUserTrading(
        @Body username:OneRequest
    ):Response<List<Trading>>

    @POST("/gettrading")
    suspend fun getTrading(
        @Body trading: Trading
    ):Response<Trading>

    @POST("/getbuyingsearch")
    suspend fun getBuyingSearch(
        @Body query:OneRequest
    ):Response<List<Trading>>

    @POST("/getsellingsearch")
    suspend fun getSellingSearch(
        @Body query:OneRequest
    ):Response<List<Trading>>

    @POST("/gettitlesearch")
    suspend fun getTitleSearch(
        @Body query:OneRequest
    ):Response<List<Trading>>

    @POST("/saveparty")
    suspend fun saveParty(
        @Body party: Party
    ):Response<SimpleResponse>
    @GET("/saveparty")
    suspend fun getPartyList():Response<List<Party>>

    @POST("/savedrop")
    suspend fun saveDrop(
        @Body dropped: Dropped
    ):Response<SimpleResponse>
    @GET("/savedrop")
    suspend fun getDrop():Response<List<Dropped>>

    @POST("/savetoday")
    suspend fun saveToday(
        @Body today: Today
    ):Response<SimpleResponse>
    @GET("/savetoday")
    suspend fun getToday():Response<Today>

    @POST("/deletedrop")
    suspend fun deleteDrop(
        @Body dropped: Dropped
    ):Response<SimpleResponse>
    @GET("/deletedrop")
    suspend fun deletion():Response<SimpleResponse>

    @POST("/togglecheck")
    suspend fun toggleCheck(
        @Body party: Party
    ):Response<ResponseBody>

    @POST("/toggledrop")
    suspend fun toggleDrop(
        @Body party: Party
    ):Response<ResponseBody>

    @POST("/togglenope")
    suspend fun toggleNope(
        @Body party: Party
    ):Response<ResponseBody>

    @POST("/getlistuser")
    suspend fun getListUser(
        @Body listUsername: ListStringRequest
    ):Response<List<User>>

    @POST("/getlistuserclub")
    suspend fun ListUserClub(
        @Body clubName:OneRequest
    ):Response<List<User>>

    @POST("/getlistuserign")
    suspend fun ListUserIgn(
        @Body ignName:OneRequest
    ):Response<List<User>>


}