package com.messaging.chatrapid.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.messaging.chatrapid.Model.MessageModel;
import com.messaging.chatrapid.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<MessageModel> messageModels;
    Context context;
    int senderViewType = 1;
    int recieverViewType = 2;

    public ChatAdapter(ArrayList<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == senderViewType){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new SenderViewHolder(view);
        }else {

         View view = LayoutInflater.from(context).inflate(R.layout.sample_reciever,parent,false);
         return new RecieverViewHolder(view);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (messageModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
            return senderViewType;
        }else {
            return recieverViewType;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MessageModel messageModel = messageModels.get(position);
        if(holder.getClass() == SenderViewHolder.class){

            ((SenderViewHolder)holder).senderMsg.setText(messageModel.getMessage());
        }else {
            ((RecieverViewHolder)holder).recieverMsg.setText(messageModel.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public  class RecieverViewHolder extends RecyclerView.ViewHolder{

        ImageView recieverPic;
        TextView  recieverMsg, recieverTime;

        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            recieverPic = itemView.findViewById(R.id.reciever_profile_pic);
            recieverMsg = itemView.findViewById(R.id.reciever_txt_id);
            recieverTime = itemView.findViewById(R.id.reciever_time);
        }
    }

    public  class SenderViewHolder extends  RecyclerView.ViewHolder{

        ImageView senderPic;
        TextView senderMsg, senderTime;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderPic = itemView.findViewById(R.id.sender_profile_pic);
            senderMsg = itemView.findViewById(R.id.sender_txt_id);
            senderTime = itemView.findViewById(R.id.sender_time);

        }
    }
}
