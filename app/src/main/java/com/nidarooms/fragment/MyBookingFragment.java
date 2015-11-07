package com.nidarooms.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nidarooms.R;

/**
 * MyBooking Fragment displays the list of bookings.
 *
 * @author Harish
 * @version 1.0 04/11/2015
 */
public class MyBookingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.my_booking_fragment,container,false);
        return v;
    }
}
