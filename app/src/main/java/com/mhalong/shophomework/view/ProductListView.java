package com.mhalong.shophomework.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhalong.shophomework.R;
import com.mhalong.shophomework.view.state.BundleSavedState;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class ProductListView extends BaseCustomViewGroup {
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.image)
    ImageView image;

    public ProductListView(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public ProductListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public ProductListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public ProductListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.product_list, this);
    }

    private void initInstances() {
        ButterKnife.bind(this);
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    public void setData(String name, String description, double price, int image) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        this.tvName.setText(name);
        this.tvDescription.setText(description);
        this.tvPrice.setText("ราคา : " + formatter.format(price) + " บาท");
        Picasso.with(getContext())
                .load(image)
                .resize(500, 300)
                .centerCrop()
                .into(this.image);

    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

}
