package com.mhalong.shophomework.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mhalong.shophomework.model.ProductListCollection;
import com.mhalong.shophomework.model.ProductListItem;
import com.mhalong.shophomework.view.ProductListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by passa on 10/11/2559.
 */

public class ProductFilterListAdapter extends BaseAdapter {

    private List<ProductListItem> list = new ArrayList<>();

    public List<ProductListItem> getProductList() {
        return list;
    }

    public void setProductList(List<ProductListItem> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return (list == null) ? 0 : list.size();
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
                list.get(position).getName(),
                list.get(position).getDescription(),
                list.get(position).getPrice(),
                list.get(position).getImage()
        );
        return item;
    }
}
