package com.hcdd.hcddchatelectronica.FireChatHelper;

import com.hcdd.hcddchatelectronica.R;

import java.util.Random;

public class ChatHelper {

    private static Random randomAvatarGenerator = new Random();
    private static int    NUMBER_OF_AVATAR=3;


    public static int  generateRandomAvatarForUser(){
        return randomAvatarGenerator.nextInt(NUMBER_OF_AVATAR);
    }


    public static int getDrawableAvatarId(int givenRandomAvatarId){

        switch (givenRandomAvatarId){

            case 0:
                return R.mipmap.ic_avatar_blue;
            case 1:
                return R.mipmap.ic_avatar_green;
            case 2:
                return R.mipmap.ic_avatar_purple;
            default:
                return R.mipmap.ic_avatar_purple;
        }
    }


}
