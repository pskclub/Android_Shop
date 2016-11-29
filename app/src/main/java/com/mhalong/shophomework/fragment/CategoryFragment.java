package com.mhalong.shophomework.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mhalong.shophomework.R;
import com.mhalong.shophomework.adapter.ProductFilterListAdapter;
import com.mhalong.shophomework.adapter.ProductListAdapter;
import com.mhalong.shophomework.manager.http.HttpShopManager;
import com.mhalong.shophomework.model.CartListCollection;
import com.mhalong.shophomework.model.ProductListCollection;
import com.mhalong.shophomework.model.ProductListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment extends Fragment {
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout layoutRefresh;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (title.equals("Highlight")) {
                    CartListCollection.getInstance().addProduct(ProductListCollection.getInstance().getProductList().get(position));
                    Toast.makeText(getActivity(), "เพิ่ม " + ProductListCollection.getInstance().getProductList().get(position).getName() + " เข้าสู่ตะกร้า", Toast.LENGTH_SHORT).show();
                } else {
                    CartListCollection.getInstance().addProduct(temp.get(position));
                    Toast.makeText(getActivity(), "เพิ่ม " + temp.get(position).getName() + " เข้าสู่ตะกร้า", Toast.LENGTH_SHORT).show();
                }

            }
        });

        layoutRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ProductListCollection.getInstance().getProductList().clear();
                Call<ResponseBody> call = HttpShopManager.getInstance().getService().getShopProduct(
                );
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        try {

                            String string = response.body().string();
                            JSONObject jsonObject = new JSONObject(string);
                            JSONObject json0 = new JSONObject(jsonObject.get("data").toString());
                            JSONArray json = json0.getJSONArray("products");
                            for (int i = 0; i < json.length(); i++) {
                                JSONObject c = json.getJSONObject(i);
                                Log.e("dasdasdasdas", "dsadasdas" + c.getString("name"));
                                ProductListItem temp2 = new ProductListItem(c.getInt("id"),
                                        c.getString("name"),
                                        c.getString("category"),
                                        c.getString("description"),
                                        c.getString("picture"),
                                        c.getDouble("price"));
                                ProductListCollection.getInstance().getProductList().add(temp2);
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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
                        layoutRefresh.setRefreshing(false);

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        layoutRefresh.setRefreshing(false);
                        Toast.makeText(getActivity(), "ไม่สามารถเชื่อมต่อเครื่อข่ายได้", Toast.LENGTH_SHORT).show();
                    }
                });



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
