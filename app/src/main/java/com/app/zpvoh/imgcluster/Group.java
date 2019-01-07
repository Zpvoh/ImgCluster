package com.app.zpvoh.imgcluster;

import java.io.Serializable;

/**
 * Created by zpvoh on 19-1-7.
 */

public class Group implements Serializable {
    public int group_id;
    public String group_name;

    public Group(int group_id, String group_name){
        this.group_id=group_id;
        this.group_name=group_name;
    }
}
