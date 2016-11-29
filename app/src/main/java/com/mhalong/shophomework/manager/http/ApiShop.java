package com.mhalong.shophomework.manager.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by passa on 29/11/2559.
 */

public interface ApiShop {

    @GET("products")
    Call<ResponseBody> getShopProduct(
    );

    @GET("products/{id}")
    Call<ResponseBody> getShopProductId(
            @Path("id") int value
    );
}
