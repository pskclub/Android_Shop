package com.mhalong.shophomework.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.mhalong.shophomework.R;
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
        List<ProductListItem> productList = new ArrayList<>();

        ProductListItem temp = new ProductListItem(1, "M9A3", "Beretta", "Semi auto pistol.", R.drawable.a01, 500);
        productList.add(temp);

        ProductListItem temp1 = new ProductListItem(2, "92FS", "Beretta", "US Military contract--check. Renewed US", R.drawable.a02, 190);
        productList.add(temp1);

        ProductListItem temp2 = new ProductListItem(3, "92FS Centenial", "Beretta", "It's first semi-automatic pistol.", R.drawable.a03, 1900);
        productList.add(temp2);

        ProductListItem temp3 = new ProductListItem(4, "92G", "Beretta", "Burnt Bronze Black Beretta 9mm", R.drawable.a04, 650);
        productList.add(temp3);

        ProductListItem temp4 = new ProductListItem(5, "96A1", "Beretta", "New for 2010 the Model 96A1", R.drawable.a05, 680);
        productList.add(temp4);

        ProductListItem temp5 = new ProductListItem(6, "M&P9 shield", "Smith&Wesson", "NEW IN THE BOX,BERETTA 92FS INOX,STAINLESS STEEL,9MM", R.drawable.a06, 450);
        productList.add(temp5);

        ProductListItem temp6 = new ProductListItem(7, "M&P22 compact", "Smith&Wesson", "Precision built to be the most accurate and reliable firearms", R.drawable.a07, 390);
        productList.add(temp6);

        ProductListItem temp7 = new ProductListItem(8, "SW22 victory ", "Smith&Wesson", "The SW22 VICTORY® is constructed on a single-action,", R.drawable.a08, 410);
        productList.add(temp7);

        ProductListItem temp8 = new ProductListItem(9, "Engraved-1911", "Smith&Wesson", "Engraved, Wooden Presentation Case", R.drawable.a09, 1220);
        productList.add(temp8);

        ProductListItem temp9 = new ProductListItem(10, "SW1911 Pro series", "Smith&Wesson", "Completing the line between main production", R.drawable.a10, 1230);
        productList.add(temp9);

        ProductListCollection.getInstance().setProductList(productList);

        productAdapter = new ProductListAdapter();
        listView.setAdapter(productAdapter);

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
