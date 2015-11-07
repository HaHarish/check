package com.nidarooms.baseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nidarooms.R;
import com.nidarooms.pojo.Pojo;

import java.util.ArrayList;

/**
 * BaseAdapter for Country Code Selection.
 *
 * @author Harish
 * @version 1.0 04/11/15
 */
public class CountryCodeSelectionBaseAdapter extends BaseAdapter {

    private ArrayList<Pojo> listData;
    private LayoutInflater layoutInflater;

    public CountryCodeSelectionBaseAdapter(Context aContext, ArrayList<Pojo> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.custom_country_code_selection, null);
            holder = new ViewHolder();
            holder.countryCode = (TextView) convertView.findViewById(R.id.textview_country_code);
            holder.countryName = (TextView) convertView.findViewById(R.id.textview_country_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.countryCode.setText(listData.get(position).getCountryCode());
        holder.countryName.setText(listData.get(position).getCountryName());

        return convertView;
    }

    static class ViewHolder {

        TextView countryCode;
        TextView countryName;

    }
}