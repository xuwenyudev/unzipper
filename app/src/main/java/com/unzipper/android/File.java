package com.unzipper.android;

/**
 * Created by 23229 on 2020/2/12.
 */

public class File {
    private String name;
    private int iconId;

    public File(String name, int iconId){
        this.name = name;
        this.iconId = iconId;
    }

    public String getName(){
        return name;
    }

    public int getIconId(){
        return iconId;
    }
}
