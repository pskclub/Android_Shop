package com.mhalong.shophomework.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mhalong.shophomework.model.ProductListCollection;
import com.mhalong.shophomework.view.ProductListView;

/**
 * Created by passa on 10/11/2559.
 */

public class ProductListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return (ProductListCollection.getInstance().getProductList() == null) ? 0 : ProductListCollection.getInstance().getProductList().size();
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
        ProductListView item;
        if (convertView != null) {
            item = (ProductListView) convertView;
        } else {
            item = new ProductListView(parent.getContext());

        }
        item.setData(
                ProductListCollection.getInstance().getProductList().get(position).getName(),
                ProductListCollection.getInstance().getProductList().get(position).getDescription(),
                ProductListCollection.getInstance().getProductList().get(position).getPrice(),
                ProductListCollection.getInstance().getProductList().get(position).getImage()
        );
        return item;
    }
}
