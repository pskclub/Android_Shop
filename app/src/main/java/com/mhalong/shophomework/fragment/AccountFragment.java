package com.mhalong.shophomework.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.mhalong.shophomework.R;
import com.mhalong.shophomework.activity.MainActivity;
import com.mhalong.shophomework.model.ProductListCollection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class AccountFragment extends Fragment {
    private Toolbar toolbar;
    @BindView(R.id.btn_login)
    AppCompatButton btn_login;
    @BindView(R.id.btn_logout)
    AppCompatButton btn_logout;
    @BindView(R.id.input_email)
    EditText input_email;
    @BindView(R.id.input_password)
    EditText input_password;
    @BindView(R.id.layoutLogin)
    LinearLayout layoutLogin;
    @BindView(R.id.layoutMember)
    LinearLayout layoutMember;

    public AccountFragment() {
        super();
    }

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, rootView);


        initInstances(rootView, savedInstanceState);
        if (ProductListCollection.getInstance().getMemnber() == 1) {
            getActivity().setTitle("มุมสมาชิก");
            layoutLogin.setVisibility(View.GONE);
            layoutMember.setVisibility(View.VISIBLE);
        } else {
            getActivity().setTitle("เข้าสู่ระบบ");
        }
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_email.getText().toString().equals("admin@admin.com") && input_password.getText().toString().equals("admin")) {
                    Toast.makeText(getActivity(), "ยินดีต้อนรับเข้าสู่ระบบ", Toast.LENGTH_SHORT).show();
                    ProductListCollection.getInstance().setMemnber(1);
                    getActivity().setTitle("มุมสมาชิก");
                    layoutLogin.setVisibility(View.GONE);
                    layoutMember.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(getActivity(), "ข้อมูลไม่ถูกต้องกรุณาลองใหม่อีกครั้ง", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductListCollection.getInstance().setMemnber(0);
                Toast.makeText(getActivity(), "ออกจากระบบแล้ว", Toast.LENGTH_SHORT).show();
                getActivity().setTitle("เข้าสู่ระบบ");
                layoutLogin.setVisibility(View.VISIBLE);
                layoutMember.setVisibility(View.GONE);
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
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

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
