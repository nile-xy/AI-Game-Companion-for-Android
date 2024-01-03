package com.shashank.gamify.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shashank.gamify.R
import com.shashank.gamify.data.models.ChatPostBody
import com.shashank.gamify.databinding.ListItemChatMessageReceivedBinding
import com.shashank.gamify.databinding.ListItemChatMessageSentBinding
import com.shashank.gamify.ui.viewmodels.ChatViewModel
import com.shashank.gamify.utils.AppUtils
import com.shashank.gamify.utils.ChatRole
import com.shashank.gamify.utils.copyTextToClipboard

class ChatListAdapter(private val mViewModel: ChatViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_MESSAGE_SENT = 1
        private const val VIEW_TYPE_MESSAGE_RECEIVED = 2
    }

    private var chatList = ArrayList<ChatPostBody.Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MESSAGE_SENT -> {
                val binding: ListItemChatMessageSentBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_item_chat_message_sent,
                    parent,
                    false
                )
                MessageSentViewHolder(binding)
            }
            else -> {
                val binding: ListItemChatMessageReceivedBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.list_item_chat_message_received,
                    parent,
                    false
                )
                MessageReceivedViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is MessageSentViewHolder -> {
                chatList[position].let { holder.bindItems(it, position) }
            }
            is MessageReceivedViewHolder -> {
                chatList[position].let { holder.bindItems(it, position) }
            }
        }
    }

    override fun getItemCount(): Int = chatList.size

    override fun getItemViewType(position: Int): Int =
        when (chatList[position].role) {
            ChatRole.USER.name.lowercase() -> VIEW_TYPE_MESSAGE_SENT
            else -> VIEW_TYPE_MESSAGE_RECEIVED
        }

    fun addItems(items: List<ChatPostBody.Message>) {
        chatList = items as ArrayList<ChatPostBody.Message>
        notifyDataSetChanged()
    }

    inner class MessageSentViewHolder(private val binding: ListItemChatMessageSentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindItems(message: ChatPostBody.Message, itemPosition: Int) {
            binding.apply {
                tvMessageSent.text = message.content.substringAfterLast("\n")
                tvMessageSent.setOnLongClickListener {
                    it.context.copyTextToClipboard(tvMessageSent.text.toString())
                    true
                }
            }
        }
    }

    inner class MessageReceivedViewHolder(private val binding: ListItemChatMessageReceivedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindItems(message: ChatPostBody.Message, itemPosition: Int) {
            binding.apply {
                tvMessageReceived.text =
                    if (itemPosition == 0) mViewModel.textCopied else message.content
                tvMessageReceived.setOnLongClickListener {
                    it.context.copyTextToClipboard(tvMessageReceived.text.toString())
                    true
                }
                tvCopy.setOnClickListener {
                    it.context.copyTextToClipboard(tvMessageReceived.text.toString())
                }
                tvShare.setOnClickListener {
                    AppUtils.shareContent(it.context, tvMessageReceived.text.toString())
                }
            }
        }

    }
}
