package com.mhalong.shophomework.fragment;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mhalong.shophomework.R;
import com.mhalong.shophomework.adapter.ProductFilterListAdapter;
import com.mhalong.shophomework.model.CartListCollection;
import com.mhalong.shophomework.model.ProductListCollection;
import com.mhalong.shophomework.model.ProductListItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SearchFragment extends Fragment {
    private Toolbar toolbar;
    @BindView(R.id.listView)
    ListView listView;

    @BindView(R.id.searchView)
    SearchView searchView;

    ProductFilterListAdapter productFilterAdapter;
    List<ProductListItem> temp;

    public SearchFragment() {
        super();
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);
        initInstances(rootView, savedInstanceState);
        getActivity().setTitle("ค้นหา");
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                temp = new ArrayList<>();
                for (int i = 0; i < ProductListCollection.getInstance().getProductList().size(); i++) {
                    try {
                        if (newText.equalsIgnoreCase(
                                ProductListCollection.getInstance().getProductList().get(i).getName().subSequence(0, newText.length()).toString())
                                || newText.equalsIgnoreCase(
                                ProductListCollection.getInstance().getProductList().get(i).getCategory().subSequence(0, newText.length()).toString())) {
                            temp.add(ProductListCollection.getInstance()
                                    .getProductList().get(i));
                        }
                    } catch (Exception ignored) {
                    }

                }

                productFilterAdapter = new ProductFilterListAdapter();
                productFilterAdapter.setProductList(temp);
                listView.setAdapter(productFilterAdapter);
                productFilterAdapter.notifyDataSetChanged();
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CartListCollection.getInstance().addProduct(temp.get(position));
                Toast.makeText(getActivity(), "เพิ่ม " + temp.get(position).getName() + " เข้าสู่ตะกร้า", Toast.LENGTH_SHORT).show();
            }
        });

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
