package com.self.categoryprototype.ui.dashboard;

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
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.self.categoryprototype.R;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    TabLayout tabLayout;
    private LinearLayout sliderDotspanel;
    private DashboardViewModel dashboardViewModel;
    private TextView[] dots;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final ViewPager2 vp = root.findViewById(R.id.vp);
        sliderDotspanel = (LinearLayout) root.findViewById(R.id.ll_indicator);
        tabLayout = root.findViewById(R.id.tabs);

        final List<String> list = new ArrayList<>();
        list.add("First Screen");
        list.add("Second Screen");
        list.add("Third Screen");
        list.add("Fourth Screen");
        list.add("Fifth Screen");
        list.add("Sixth Screen");

        vp.setAdapter(new ViewPagerAdapter(getActivity(), list, vp));
        new TabLayoutMediator(tabLayout, vp,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(list.get(position));
                    }
                }).attach();
        //setupViewPagerIndicator(list.size(), list);

        vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
             /*   for (int i = 0; i < list.size(); i++) {
                    dots[i].setText(list.get(i));
                    dots[i].setEnabled(false);
                }
                dots[position].setText(list.get(position));
                dots[position].setEnabled(true);*/

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });
        return root;
    }

    private void setupViewPagerIndicator(int count, List<String> list) {
        dots = new TextView[list.size()];

        for (int i = 0; i < list.size(); i++) {
            dots[i] = new TextView(getActivity());
            dots[i].setText(list.get(i));

            // LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            // params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i]);
        }
        dots[0].setText(list.get(0));
    }

}