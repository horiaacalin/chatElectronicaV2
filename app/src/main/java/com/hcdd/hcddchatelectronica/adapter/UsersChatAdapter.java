package com.hcdd.hcddchatelectronica.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hcdd.hcddchatelectronica.FireChatHelper.ReferenceUrl;
import com.hcdd.hcddchatelectronica.model.UsersChatModel;
import com.hcdd.hcddchatelectronica.ui.ChatActivity;
import com.hcdd.hcddchatelectronica.FireChatHelper.ChatHelper;
import com.hcdd.hcddchatelectronica.R;

import java.util.List;


public class UsersChatAdapter extends RecyclerView.Adapter<UsersChatAdapter.ViewHolderUsers> {

    private List<UsersChatModel> mFireChatUsers;
    private Context mContext;
    private String mCurrentUserName;
    private String mCurrentUserCreatedAt;

    public UsersChatAdapter(Context context, List<UsersChatModel> fireChatUsers) {
        mFireChatUsers=fireChatUsers;
        mContext=context;
    }

    @Override
    public ViewHolderUsers onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderUsers(mContext,LayoutInflater.from(parent.getContext()).inflate(R.layout.user_profile, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolderUsers holder, int position) {

        UsersChatModel fireChatUser=mFireChatUsers.get(position);


        int userAvatarId= ChatHelper.getDrawableAvatarId(fireChatUser.getAvatarId());
        Drawable  avatarDrawable= ContextCompat.getDrawable(mContext,userAvatarId);
        holder.getUserPhoto().setImageDrawable(avatarDrawable);


        holder.getUserFirstName().setText(fireChatUser.getFirstName());


        holder.getStatusConnection().setText(fireChatUser.getConnection());


        if(fireChatUser.getConnection().equals(ReferenceUrl.KEY_ONLINE)) {

            holder.getStatusConnection().setTextColor(Color.parseColor("#00FF00"));
        }else {

            holder.getStatusConnection().setTextColor(Color.parseColor("#FF0000"));
        }

    }

    @Override
    public int getItemCount() {
        return mFireChatUsers.size();
    }

    public void refill(UsersChatModel users) {


        mFireChatUsers.add(users);
        notifyDataSetChanged();
    }

    public void setNameAndCreatedAt(String userName, String createdAt) {


        mCurrentUserName=userName;
        mCurrentUserCreatedAt=createdAt;
    }

    public void changeUser(int index, UsersChatModel user) {


        mFireChatUsers.set(index,user);
        notifyDataSetChanged();
    }




    public class ViewHolderUsers extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mUserPhoto;
        private TextView mUserFirstName;
        private TextView mStatusConnection;
        private Context mContextViewHolder;

        public ViewHolderUsers(Context context, View itemView) {
            super(itemView);
            mUserPhoto=(ImageView)itemView.findViewById(R.id.userPhotoProfile);
            mUserFirstName=(TextView)itemView.findViewById(R.id.userFirstNameProfile);
            mStatusConnection=(TextView)itemView.findViewById(R.id.connectionStatus);
            mContextViewHolder=context;


            itemView.setOnClickListener(this);
        }

        public ImageView getUserPhoto() {
            return mUserPhoto;
        }

        public TextView getUserFirstName() {
            return mUserFirstName;
        }
        public TextView getStatusConnection() {
            return mStatusConnection;
        }


        @Override
        public void onClick(View view) {



            int position=getLayoutPosition();

            UsersChatModel user=mFireChatUsers.get(position);


            user.setCurrentUserName(mCurrentUserName);
            user.setCurrentUserCreatedAt(mCurrentUserCreatedAt);


            Intent chatIntent=new Intent(mContextViewHolder, ChatActivity.class);


            chatIntent.putExtra(ReferenceUrl.KEY_PASS_USERS_INFO,user);


            mContextViewHolder.startActivity(chatIntent);

        }
    }

}
