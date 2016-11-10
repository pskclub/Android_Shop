package com.mhalong.shophomework.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mhalong.shophomework.R;
import com.mhalong.shophomework.model.CartListCollection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeliveryActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @BindView(R.id.btn_payment)
    AppCompatButton btn_payment;
    @BindView(R.id.input_name)
    EditText input_name;
    @BindView(R.id.input_address)
    EditText input_address;
    @BindView(R.id.input_tel)
    EditText input_tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ช่องทางการจัดส่ง");
        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_name.getText().toString().equals("") || input_address.getText().toString().equals("") || input_tel.getText().toString().equals("")) {
                    Toast.makeText(DeliveryActivity.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                } else {
                    CartListCollection.getInstance().setName(input_name.getText().toString());
                    CartListCollection.getInstance().setAddress(input_address.getText().toString());
                    CartListCollection.getInstance().setTel(input_tel.getText().toString());
                    Intent myIntent = new Intent(DeliveryActivity.this, PaymentActivity.class);
                    startActivity(myIntent);
                }


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
