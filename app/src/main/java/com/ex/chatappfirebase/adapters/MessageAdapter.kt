package com.ex.chatappfirebase.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ex.chatappfirebase.R
import com.ex.chatappfirebase.data.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class MessageAdapter(val context: Context, val messageList : List<Message>,val image : String) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {
    private lateinit var firebaseAuth: FirebaseAuth
    class MessageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var circleImageView_chat : CircleImageView ?= null
        var message : TextView ?= null



        init {
            circleImageView_chat = itemView.findViewById(R.id.receiver_image_left)
            message = itemView.findViewById(R.id.text_message_chat_left)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
         if(viewType == 1){
            return MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_right,parent,false))
        }else{
            return MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_left,parent,false))
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message : Message = messageList[position]


        holder.message!!.text = message.message


        Picasso.get().load(image).into(holder.circleImageView_chat)

    }

    override fun getItemCount(): Int {
       return messageList.size
    }

    override fun getItemViewType(position: Int): Int {
        firebaseAuth = Firebase.auth
        return if(messageList[position].sender == firebaseAuth.currentUser!!.uid){
            1
        }else{
            0
        }
    }
}