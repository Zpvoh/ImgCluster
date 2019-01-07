package com.app.zpvoh.imgcluster;

import java.io.Serializable;

/**
 * Created by zpvoh on 19-1-7.
 */

public class Image implements Serializable {
    public int img_id;
    public int group_id;
    public String time;
    public String name;
    public String path;

    public Image(int img_id, int group_id, String time, String name, String path){
        this.img_id=img_id;
        this.group_id=group_id;
        this.time=time;
        this.name=name;
        this.path=path;
    }
}
