package com.mhalong.shophomework.model;

import android.content.Context;

import com.mhalong.shophomework.manager.Contextor;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ProductListCollection {

    private static ProductListCollection instance;

    public static ProductListCollection getInstance() {
        if (instance == null)
            instance = new ProductListCollection();
        return instance;
    }

    private Context mContext;
    private List<ProductListItem> list = new ArrayList<>();

    private ProductListCollection() {
        mContext = Contextor.getInstance().getContext();
    }

    public void setProductList(List<ProductListItem> list) {
        this.list = list;
    }
}
