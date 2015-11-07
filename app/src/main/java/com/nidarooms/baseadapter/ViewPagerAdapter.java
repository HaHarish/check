package com.nidarooms.baseadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nidarooms.R;

/**
 * ViewPager Adapter for hold Country Name and Images.
 *
 * @author Harish
 * @version 1.0 04/11/15
 */
public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    String[] countryName;
    int[] countryImages;
    LayoutInflater inflater;

    public ViewPagerAdapter(Context context, String[] countryName, int[] countryImages) {
        this.context = context;
        this.countryName = countryName;
        this.countryImages = countryImages;
    }

    @Override
    public int getCount() {
        return countryName.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        TextView txtCountryName;
        ImageView imgflag;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        txtCountryName = (TextView) itemView.findViewById(R.id.population);

        // Capture position and set to the TextViews
        txtCountryName.setText(countryName[position]);

        // Locate the ImageView in viewpager_item.xml
        imgflag = (ImageView) itemView.findViewById(R.id.flag);

        // Capture position and set to the ImageView
        imgflag.setImageResource(countryImages[position]);

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((LinearLayout) object);

    }
}
