package com.nidarooms.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nidarooms.R;
import com.nidarooms.baseadapter.ViewPagerAdapter;
import com.nidarooms.home.SubLocalityActivity;

/**
 * Search Fragment to select Country, City and Near by rooms.
 *
 * @author Harish
 * @version 1.0 04/11/2015
 */
public class SearchFragment extends Fragment {

    // ViewPager for Country Selection
    ViewPager viewPager;

    // Adapter for Hold Country Images
    PagerAdapter adapter;

    // Get Country Names
    String[] countryNames;

    // Get Country Images
    int[] countryImages;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.home_search_fragment, container, false);

        countryNames = new String[]{"KUALA LUMPUR", "PHILIPPINES", "SINGAPORE", "THAILAND"};

        countryImages = new int[]{ R.drawable.kuala_lumpur_icon, R.drawable.philippines_icon,
                R.drawable.singapore_icon, R.drawable.thailand_icon };

        // Locate the ViewPager in viewpager_main.xml
        viewPager = (ViewPager) v.findViewById(R.id.pagerNew);

        // Pass results to ViewPagerAdapter Class
        adapter = new ViewPagerAdapter(getActivity(), countryNames, countryImages);

        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);

        ImageView imageView = (ImageView)v.findViewById(R.id.click);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),SubLocalityActivity.class);
                startActivity(intent);

            }
        });

        return v;
    }
}
