package ir.majj.alibaba.network

import ir.majj.alibaba.models.Page
import ir.majj.alibaba.models.User
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {
    @GET(URLs.USER)
    fun getUsers(): Call<Page<User>>
}
