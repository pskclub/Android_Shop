package com.mhalong.shophomework.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mhalong.shophomework.model.CartListCollection;
import com.mhalong.shophomework.model.ProductListCollection;
import com.mhalong.shophomework.view.CartListView;
import com.mhalong.shophomework.view.ProductListView;

/**
 * Created by passa on 10/11/2559.
 */

public class CartListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return (CartListCollection.getInstance().getProductList() == null) ? 0 : CartListCollection.getInstance().getProductList().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CartListView item;
        if (convertView != null) {
            item = (CartListView) convertView;
        } else {
            item = new CartListView(parent.getContext());

        }
        item.setData(
                CartListCollection.getInstance().getProductList().get(position).getName(),
                CartListCollection.getInstance().getProductList().get(position).getDescription(),
                CartListCollection.getInstance().getProductList().get(position).getPrice(),
                CartListCollection.getInstance().getProductList().get(position).getImage()
        );
        return item;
    }
}
