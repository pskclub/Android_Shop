package com.mhalong.shophomework.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mhalong.shophomework.R;
import com.mhalong.shophomework.adapter.CartListAdapter;
import com.mhalong.shophomework.adapter.ProductListAdapter;
import com.mhalong.shophomework.model.CartListCollection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.btn_payment)
    AppCompatButton btn_payment;
    @BindView(R.id.tvTotal)
    TextView tvTotal;

    CartListAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ตะกร้าสินค้า (" + CartListCollection.getInstance().getProductList().size() + ")");

        cartAdapter = new CartListAdapter();
        listView.setAdapter(cartAdapter);
        tvTotal.setText("ราคาทั้งหมด " + CartListCollection.getInstance().getTotalPrice() + " บาท");
        if (CartListCollection.getInstance().getProductList().size() == 0) {
            btn_payment.setVisibility(View.GONE);
            tvTotal.setVisibility(View.GONE);
        } else {
            btn_payment.setVisibility(View.VISIBLE);
            tvTotal.setVisibility(View.VISIBLE);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CartActivity.this, "ลบ " + CartListCollection.getInstance().getProductList().get(position).getName() + " ออกจากตะกร้า", Toast.LENGTH_SHORT).show();
                CartListCollection.getInstance().getProductList().remove(position);
                cartAdapter.notifyDataSetChanged();
                tvTotal.setText("ราคาทั้งหมด " + CartListCollection.getInstance().getTotalPrice() + " บาท");
                if (CartListCollection.getInstance().getProductList().size() == 0) {
                    btn_payment.setVisibility(View.GONE);
                    tvTotal.setVisibility(View.GONE);
                } else {
                    btn_payment.setVisibility(View.VISIBLE);
                    tvTotal.setVisibility(View.VISIBLE);
                }
                getSupportActionBar().setTitle("ตะกร้าสินค้า (" + CartListCollection.getInstance().getProductList().size() + ")");
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
