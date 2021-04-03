package com.food.feedex.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.food.feedex.R;
import com.food.feedex.ui.gallery.GalleryFragment;

public class ProductDetailFragment extends Fragment {
    View view;
    TextView addTv;

    public static ProductDetailFragment newInstance() {

        Bundle args = new Bundle();

        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.product_detail_layout, container, false);
        addTv = view.findViewById(R.id.productnameTv);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        addTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, GalleryFragment.newInstance()).commit();
            }
        });
    }
}
