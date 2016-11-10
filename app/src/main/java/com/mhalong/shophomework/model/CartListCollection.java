package com.mhalong.shophomework.model;

import android.content.Context;

import com.mhalong.shophomework.manager.Contextor;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class CartListCollection {

    private static CartListCollection instance;

    public static CartListCollection getInstance() {
        if (instance == null)
            instance = new CartListCollection();
        return instance;
    }

    private Context mContext;
    private List<ProductListItem> list = new ArrayList<>();
    private String address;
    private String tel;
    private String name;

    private CartListCollection() {
        mContext = Contextor.getInstance().getContext();
    }

    public List<ProductListItem> getProductList() {
        return list;
    }

    public double getTotalPrice() {
        double total = 0;
        for (int i = 0; i <= list.size() - 1; i++)
            total = total + list.get(i).getPrice();
        return total;
    }

    public void setProductList(List<ProductListItem> list) {
        this.list = list;
    }

    public void addProduct(ProductListItem product) {
        this.list.add(product);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
