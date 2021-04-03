package com.food.feedex.ui.home;

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
import com.food.feedex.ui.slideshow.SlideshowFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    TextView dateTv;
    View root;
    LinearLayout foodLin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        dateTv = root.findViewById(R.id.dateTv);
        foodLin = root.findViewById(R.id.foodLin);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        dateTv.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        foodLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, SlideshowFragment.newInstance()).commit();
            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
    }
}