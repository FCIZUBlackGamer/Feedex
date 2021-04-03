package com.food.feedex.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.food.feedex.R;

public class ProductListFragment extends Fragment {
    View view;
    LinearLayout productnameTv;

    public static ProductListFragment newInstance() {

        Bundle args = new Bundle();

        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.product_item_layout, container, false);
        productnameTv = view.findViewById(R.id.productnameTv);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        productnameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, ProductDetailFragment.newInstance()).commit();
            }
        });
    }
}
