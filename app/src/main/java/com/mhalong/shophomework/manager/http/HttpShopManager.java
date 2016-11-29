package com.mhalong.shophomework.manager.http;

import android.content.Context;

import com.mhalong.shophomework.manager.Contextor;

import retrofit2.Retrofit;

/**
 * Created by passa on 29/11/2559.
 */

public class HttpShopManager {

    private static HttpShopManager instance;

    public static HttpShopManager getInstance() {
        if (instance == null)
            instance = new HttpShopManager();
        return instance;
    }

    private Context mContext;
    private ApiShop service;

    private HttpShopManager() {
        mContext = Contextor.getInstance().getContext();
        Retrofit retofit = new Retrofit.Builder()
                .baseUrl("https://android-api.mhalong.com/api/")
                .build();
        service = retofit.create(ApiShop.class);
    }

    public ApiShop getService() {
        return service;
    }
}
