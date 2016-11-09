package com.mhalong.shophomework.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mhalong.shophomework.R;
import com.mhalong.shophomework.adapter.ProductFilterListAdapter;
import com.mhalong.shophomework.adapter.ProductListAdapter;
import com.mhalong.shophomework.model.ProductListCollection;
import com.mhalong.shophomework.model.ProductListItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryFragment extends Fragment {
    @BindView(R.id.listView)
    ListView listView;
    String title;

    ProductListAdapter productAdapter;
    ProductFilterListAdapter productFilterAdapter;
    List<ProductListItem> temp;

    public CategoryFragment() {
        super();
    }

    public static CategoryFragment newInstance(String title) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        title = getArguments().getString("title");
        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, rootView);
        initInstances(rootView, savedInstanceState);

        if (title.equals("Highlight")) {
            productAdapter = new ProductListAdapter();
            listView.setAdapter(productAdapter);
        } else {
            temp = new ArrayList<>();
            for (int i = 0; i < ProductListCollection.getInstance().getProductList().size(); i++) {
                if (title.equals(
                        ProductListCollection.getInstance().getProductList().get(i).getCategory())
                        ) {
                    temp.add(ProductListCollection.getInstance()
                            .getProductList().get(i));
                }

            }

            productFilterAdapter = new ProductFilterListAdapter();
            productFilterAdapter.setProductList(temp);
            listView.setAdapter(productFilterAdapter);
        }

        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }
}
