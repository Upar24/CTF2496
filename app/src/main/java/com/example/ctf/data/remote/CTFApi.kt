package com.example.ctf.data.remote

import com.example.ctf.data.local.entities.*
import com.example.ctf.data.remote.requests.AccountRequest
import com.example.ctf.data.remote.requests.ListStringRequest
import com.example.ctf.data.remote.requests.OneRequest
import com.example.ctf.data.remote.requests.UpdateUserRequest
import com.example.ctf.data.remote.responses.SimpleResponse
import com.example.ctf.util.listString.deleteDropPath
import com.example.ctf.util.listString.deleteTradingPath
import com.example.ctf.util.listString.deleteWallPath
import com.example.ctf.util.listString.getAllTradingPath
import com.example.ctf.util.listString.getAllUserTradingPath
import com.example.ctf.util.listString.getBuyingSearchPath
import com.example.ctf.util.listString.getChatPath
import com.example.ctf.util.listString.getDescriptionSearchPath
import com.example.ctf.util.listString.getListUserClubPath
import com.example.ctf.util.listString.getListUserIgnPath
import com.example.ctf.util.listString.getListUserPath
import com.example.ctf.util.listString.getPartyPath
import com.example.ctf.util.listString.getSellingSearchPath
import com.example.ctf.util.listString.getTitleSearchPath
import com.example.ctf.util.listString.getTradingPath
import com.example.ctf.util.listString.getUserPath
import com.example.ctf.util.listString.getWallPath
import com.example.ctf.util.listString.loginPath
import com.example.ctf.util.listString.registerPath
import com.example.ctf.util.listString.saveChatPath
import com.example.ctf.util.listString.saveDropPath
import com.example.ctf.util.listString.savePartyPath
import com.example.ctf.util.listString.saveTodayPath
import com.example.ctf.util.listString.saveTradingPath
import com.example.ctf.util.listString.saveWallPath
import com.example.ctf.util.listString.toggleCheckPath
import com.example.ctf.util.listString.toggleDropPath
import com.example.ctf.util.listString.toggleNopePath
import com.example.ctf.util.listString.updateUserPath
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CTFApi {

    @POST(registerPath)
    suspend fun register(
        @Body registerRequest: AccountRequest
    ): Response<SimpleResponse>

    @POST(loginPath)
    suspend fun login(
        @Body loginRequest: AccountRequest
    ):Response<SimpleResponse>

    @POST(getUserPath)
    suspend fun getUser(
        @Body oneRequest: OneRequest
    ):Response<User>

    @POST(updateUserPath)
    suspend fun updateProfile(
        @Body updateUserReq: UpdateUserRequest
    ):Response<SimpleResponse>

    @POST(saveWallPath)
    suspend fun saveWall(
        @Body wall: Wall
    ):Response<SimpleResponse>

    @POST(getWallPath)
    suspend fun getWall(
        @Body getRequest : OneRequest
    ):Response<List<Wall>>

    @POST(deleteWallPath)
    suspend fun deleteWall(
        @Body wall:Wall
    ):Response<SimpleResponse>

    @POST(saveChatPath)
    suspend fun saveChat(
        @Body chat: Chat
    ):Response<SimpleResponse>

    @GET(getChatPath)
    suspend fun getChat():Response<List<Chat>>

    @POST(saveTradingPath)
    suspend fun saveTrading(
        @Body trading: Trading
    ):Response<SimpleResponse>

    @POST(deleteTradingPath)
    suspend fun deleteTrading(
        @Body trading:Trading
    ):Response<SimpleResponse>

    @GET(getAllTradingPath)
    suspend fun getAllTrading():Response<List<Trading>>

    @POST(getAllUserTradingPath)
    suspend fun getAllUserTrading(
        @Body username:OneRequest
    ):Response<List<Trading>>

    @POST(getTradingPath)
    suspend fun getTrading(
        @Body trading: Trading
    ):Response<Trading>

    @POST(getBuyingSearchPath)
    suspend fun getBuyingSearch(
        @Body query:OneRequest
    ):Response<List<Trading>>

    @POST(getSellingSearchPath)
    suspend fun getSellingSearch(
        @Body query:OneRequest
    ):Response<List<Trading>>

    @POST(getTitleSearchPath)
    suspend fun getTitleSearch(
        @Body query:OneRequest
    ):Response<List<Trading>>

    @POST(getDescriptionSearchPath)
    suspend fun getDescriptionSearch(
        @Body query:OneRequest
    ):Response<List<Trading>>

    @POST(savePartyPath)
    suspend fun saveParty(
        @Body party: Party
    ):Response<SimpleResponse>
    @POST(getPartyPath)
    suspend fun getPartyList(
        @Body query: OneRequest
    ):Response<List<Party>>

    @POST(saveDropPath)
    suspend fun saveDrop(
        @Body dropped: Dropped
    ):Response<SimpleResponse>
    @GET(saveDropPath)
    suspend fun getDrop():Response<List<Dropped>>

    @POST(saveTodayPath)
    suspend fun saveToday(
        @Body today: Today
    ):Response<SimpleResponse>
    @GET(saveTodayPath)
    suspend fun getToday():Response<Today>

    @POST(deleteDropPath)
    suspend fun deleteDrop(
        @Body dropped: Dropped
    ):Response<SimpleResponse>
    @GET(deleteDropPath)
    suspend fun deletion():Response<SimpleResponse>

    @POST(toggleCheckPath)
    suspend fun toggleCheck(
        @Body party: Party
    ):Response<ResponseBody>

    @POST(toggleDropPath)
    suspend fun toggleDrop(
        @Body party: Party
    ):Response<ResponseBody>

    @POST(toggleNopePath)
    suspend fun toggleNope(
        @Body party: Party
    ):Response<ResponseBody>

    @POST(getListUserPath)
    suspend fun getListUser(
        @Body listUsername: ListStringRequest
    ):Response<List<User>>

    @POST(getListUserClubPath)
    suspend fun ListUserClub(
        @Body clubName:OneRequest
    ):Response<List<User>>

    @POST(getListUserIgnPath)
    suspend fun ListUserIgn(
        @Body ignName:OneRequest
    ):Response<List<User>>


}