package com.app.zpvoh.imgcluster;

import java.util.ArrayList;

/**
 * Created by zpvoh on 19-1-7.
 */

public class Deal {
    public static boolean signUp(String email, String username, String pass){
        //TODO:fill up the code
        return false;
    }

    public static User signIn(String email, String pass){
        //TODO:fill up the code
        return null;
    }

    public static ArrayList<Group> getUserGroups(int uid){
        //TODO:fill up the code
        return new ArrayList<>();
    }

    public static ArrayList<Image> getImageByGroup(int group_id){
        //TODO:fill up the code
        return new ArrayList<>();
    }

    public static ArrayList<Comment> getCommentByImage(int img_id){
        //TODO:fill up the code
        return new ArrayList<>();
    }

    public static Group createGroup(String name){
        //TODO:fill up the code
        return null;
    }

    public static String getValidNumber(int group_id){
        //TODO:fill up the code
        return null;
    }

    public static boolean uploadImage(){
        //TODO:fill up the code
        return false;
    }

    public static Comment submitComment(int uid, int img_id, String content){
        //TODO:fill up the code
        return null;
    }

    public static Group joinGroup(int uid, String validNumber){
        //TODO:fill up the code
        return null;
    }

}
