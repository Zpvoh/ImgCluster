package com.app.zpvoh.imgcluster;

import java.io.Serializable;

/**
 * Created by zpvoh on 19-1-7.
 */

public class Comment implements Serializable{
    public String username;
    public String content;

    public Comment(String username, String content){
        this.username=username;
        this.content=content;
    }
}
