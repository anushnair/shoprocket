package com.bottlerocket.androiddevtest.shoprocket.framework;

import com.bottlerocket.androiddevtest.shoprocket.framework.models.Store;

import java.util.ArrayList;

public class Global {

    static Global instance;
    ArrayList<Store> gStoreList = new ArrayList<>();

    public Global(){

    }

    public static Global getInstance(){

        if(null==instance){
;
            instance = new Global();
        }

        return instance;
    }

    public ArrayList<Store> getStoreList() {
        return gStoreList;
    }

    public void setStoreList(ArrayList<Store> gStoreList) {
        this.gStoreList = gStoreList;
    }



}
