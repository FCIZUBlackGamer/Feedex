package com.food.feedex.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.food.feedex.R;
import com.food.feedex.ui.ProductListFragment;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    View root;
    LinearLayout ramadanLin, menuLin;

    public static SlideshowFragment newInstance() {

        Bundle args = new Bundle();

        SlideshowFragment fragment = new SlideshowFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        root = inflater.inflate(R.layout.food_menu, container, false);
        ramadanLin = root.findViewById(R.id.ramadanLin);
        menuLin = root.findViewById(R.id.menuLin);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        menuLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, ProductListFragment.newInstance()).commit();
            }
        });

        ramadanLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, ProductListFragment.newInstance()).commit();
            }
        });
    }
}