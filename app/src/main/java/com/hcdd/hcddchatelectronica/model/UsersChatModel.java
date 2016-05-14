package com.hcdd.hcddchatelectronica.model;

import android.os.Parcel;
import android.os.Parcelable;


public class UsersChatModel implements Parcelable{

    private String firstName;
    private String provider;
    private String userEmail;
    private String createdAt;
    private String connection;
    private int    avatarId;
    private String mRecipientUid;


    private String mCurrentUserName;
    private String mCurrentUserUid;
    private String mCurrentUserEmail;
    private String mCurrentUserCreatedAt;

    public UsersChatModel(){

    }

    private UsersChatModel(Parcel parcelIn) {

        firstName=parcelIn.readString();
        provider=parcelIn.readString();
        userEmail=parcelIn.readString();
        createdAt=parcelIn.readString();
        connection=parcelIn.readString();
        avatarId=parcelIn.readInt();
        mRecipientUid=parcelIn.readString();
        mCurrentUserName=parcelIn.readString();
        mCurrentUserUid=parcelIn.readString();
        mCurrentUserEmail=parcelIn.readString();
        mCurrentUserCreatedAt=parcelIn.readString();

    }


    public String getFirstName() {
        return firstName;
    }

    public String getUserEmail() {
        //Log.e("user email  ", userEmail);
        return userEmail;
    }

    public String getProvider() {
       return provider;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public String getConnection(){
        return connection;
    }

    public int    getAvatarId(){
        return avatarId;
    }

    public String getRecipientUid(){
        return mRecipientUid;
    }

    public void setRecipientUid(String givenUserUid){
        mRecipientUid =givenUserUid;
    }



    public void setCurrentUserName(String currentUserName){
        mCurrentUserName=currentUserName;
    }

    public void setCurrentUserEmail(String currentUserEmail) {
        mCurrentUserEmail = currentUserEmail;
    }

    public void setCurrentUserCreatedAt(String currentUserCreatedAt) {
        mCurrentUserCreatedAt = currentUserCreatedAt;
    }

    public void setCurrentUserUid(String currentUserUid){
        mCurrentUserUid=currentUserUid;
    }

    public String getCurrentUserName(){
        return mCurrentUserName;
    }

    public String getCurrentUserEmail() {
        //Log.e("current user email  ", mCurrentUserEmail);
        return mCurrentUserEmail;
    }

    public String getCurrentUserCreatedAt() {
        return mCurrentUserCreatedAt;
    }

    public String getCurrentUserUid(){
        return mCurrentUserUid;
    }


    public String getChatRef(){
        return createUniqueChatRef();
    }



    private String createUniqueChatRef(){
        String uniqueChatRef="";
        if(createdAtCurrentUser()>createdAtRecipient()){
            uniqueChatRef=cleanEmailAddress(getCurrentUserEmail())+"-"+cleanEmailAddress(getUserEmail());
        }else {

            uniqueChatRef=cleanEmailAddress(getUserEmail())+"-"+cleanEmailAddress(getCurrentUserEmail());
        }
        return uniqueChatRef;
    }

    private long createdAtCurrentUser(){
        return Long.parseLong(getCurrentUserCreatedAt());
    }

    private long createdAtRecipient(){
        return Long.parseLong(getCreatedAt());
    }

    private String cleanEmailAddress(String email){


        return email.replace(".","-");

    }


    /*Parcelable*/

    @Override
    public int describeContents() {
        return 0; //ignore
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {


        parcel.writeString(firstName);
        parcel.writeString(provider);
        parcel.writeString(userEmail);
        parcel.writeString(createdAt);
        parcel.writeString(connection);
        parcel.writeInt(avatarId);
        parcel.writeString(mRecipientUid);
        parcel.writeString(mCurrentUserName);
        parcel.writeString(mCurrentUserUid);
        parcel.writeString(mCurrentUserEmail);
        parcel.writeString(mCurrentUserCreatedAt);

    }



    public static final Creator<UsersChatModel> CREATOR= new Creator<UsersChatModel>() {
        @Override
        public UsersChatModel createFromParcel(Parcel parcel) {
            return new UsersChatModel(parcel);
        }

        @Override
        public UsersChatModel[] newArray(int size) {
            return new UsersChatModel[size];
        }
    };
}
