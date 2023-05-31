package com.example.submissiongithub.api

import com.example.submissiongithub.data.model.DetailUserResponse
import com.example.submissiongithub.data.model.User
import com.example.submissiongithub.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}