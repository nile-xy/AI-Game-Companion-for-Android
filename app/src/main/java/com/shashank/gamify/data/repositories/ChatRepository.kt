package com.shashank.gamify.data.repositories

import com.shashank.gamify.data.models.ChatPostBody
import com.shashank.gamify.data.models.ChatResponseBody
import com.shashank.gamify.data.network.ApiInterface
import com.shashank.gamify.data.network.SafeApiRequest
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val api: ApiInterface
) : SafeApiRequest() {

    suspend fun sendMessage(
        chatPostBody: ChatPostBody
    ): ChatResponseBody = apiRequest {
        api.sendMessage(chatPostBody)
    }
}