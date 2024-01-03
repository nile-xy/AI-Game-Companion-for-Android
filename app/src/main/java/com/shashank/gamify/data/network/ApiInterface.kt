package com.shashank.gamify.data.network

import com.shashank.gamify.data.models.ChatPostBody
import com.shashank.gamify.data.models.ChatResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    // <editor-fold desc="Post Requests">

    @POST("v1/chat/completions")
    suspend fun sendMessage(
        @Body chatPostBody: ChatPostBody
    ): Response<ChatResponseBody>


    // </editor-fold>


}