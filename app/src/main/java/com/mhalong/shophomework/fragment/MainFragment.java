package com.mhalong.shophomework.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mhalong.shophomework.R;
import com.mhalong.shophomework.activity.CaptureActivity;
import com.mhalong.shophomework.activity.CartActivity;
import com.mhalong.shophomework.activity.MainActivity;
import com.mhalong.shophomework.bus.ResultBus;
import com.mhalong.shophomework.event.ActivityResultEvent;
import com.mhalong.shophomework.manager.http.HttpShopManager;
import com.mhalong.shophomework.model.ProductListCollection;
import com.mhalong.shophomework.model.ProductListItem;
import com.squareup.otto.Subscribe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MainFragment extends Fragment {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView, savedInstanceState);
        getActivity().setTitle(R.string.app_name);
        return rootView;
    }

    private void setupViewPager(ViewPager viewPager, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
            adapter.addFrag(new CategoryFragment().newInstance("Highlight"), "Highlight");
            adapter.addFrag(new CategoryFragment().newInstance("Colt"), "Colt");
            adapter.addFrag(new CategoryFragment().newInstance("Glock"), "Glock");
            adapter.addFrag(new CategoryFragment().newInstance("Smith&Wesson"), "Smith&Wesson");
            adapter.addFrag(new CategoryFragment().newInstance("Beretta"), "Beretta");
            viewPager.setAdapter(adapter);
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, final Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);



        if (savedInstanceState == null) {
            viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
            List<ProductListItem> productList = new ArrayList<>();
            ProductListCollection.getInstance().setProductList(productList);

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

                    setupViewPager(viewPager, savedInstanceState);

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getActivity(), "ไม่สามารถเชื่อมต่อเครื่อข่ายได้", Toast.LENGTH_SHORT).show();
                }
            });
        }

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }
    private Object mActivityResultSubscriber = new Object() {
        @Subscribe
        public void onActivityResultReceived(ActivityResultEvent event) {
            int requestCode = event.getRequestCode();
            int resultCode = event.getResultCode();
            Intent data = event.getData();
            onActivityResult(requestCode, resultCode, data);
        }
    };
    @Override
    public void onStart() {
        super.onStart();
        ResultBus.getInstance().register(mActivityResultSubscriber);
    }

    @Override
    public void onStop() {
        super.onStop();
        ResultBus.getInstance().unregister(mActivityResultSubscriber);
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.cart, menu);  // Use filter.xml from step 1
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result.getContents() != null) {
//            Toast.makeText(getActivity(), result.getContents(), Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer, SearchFragment.newInstance(result.getContents().toString()))
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cart) {
            Intent myIntent = new Intent(getActivity(), CartActivity.class);
            getActivity().startActivity(myIntent);
            return true;
        }
        if (id == R.id.action_qrcode) {
            new IntentIntegrator(getActivity()).setCaptureActivity(CaptureActivity.class).initiateScan();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
