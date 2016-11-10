
package com.mhalong.shophomework.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

public class PaymentActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @BindView(R.id.btn_payment)
    AppCompatButton btn_payment;
    @BindView(R.id.input_credit)
    EditText input_credit;
    @BindView(R.id.input_date)
    EditText input_date;
    @BindView(R.id.input_ccv)
    EditText input_ccv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ชำระเงิน");

        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_ccv.getText().toString().equals("") || input_credit.getText().toString().equals("") || input_date.getText().toString().equals("")) {
                    Toast.makeText(PaymentActivity.this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(PaymentActivity.this);
                    builder1.setMessage("ขอบคุณสำหรับการสั่งซื้อ\nเราจะจัดส่งที่อยู่ไปยัง \n"
                            + "ชื่อ : " + CartListCollection.getInstance().getName()
                            + "\n" + "ที่อยู่ : " + CartListCollection.getInstance().getAddress() + "\n"
                            + "เบอร์โทร : " + CartListCollection.getInstance().getTel());

                    builder1.setPositiveButton(
                            "ตกลง",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                    CartListCollection.getInstance().getProductList().clear();
                                    Intent myIntent = new Intent(PaymentActivity.this, MainActivity.class);
                                    startActivity(myIntent);
                                }
                            });


                    AlertDialog alert11 = builder1.create();
                    alert11.setCanceledOnTouchOutside(false);
                    alert11.show();
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
