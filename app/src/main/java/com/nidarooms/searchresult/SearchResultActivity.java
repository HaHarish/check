package com.nidarooms.searchresult;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nidarooms.R;
import com.nidarooms.fragment.SearchListListViewFragment;
import com.nidarooms.fragment.SearchListMapViewFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Search Result Activity for Display Map and List View.
 * Display Hotels list in Map View.
 * Display Hotels list in List View.
 * The User's have to choose the hotels for booking process.
 *
 * @author Harish
 * @version 1.0 04/11/15
 *
 */
public class SearchResultActivity extends AppCompatActivity
        implements FragmentManager.OnBackStackChangedListener {

    /** A handler object, used for deferring UI operations. */
    private Handler mHandler = new Handler();

    /** Whether or not we're showing the back of the card (otherwise showing the front). */
    private boolean mShowingBack = false;

    /** Floating Action Button for display Map View of the Hotel */
    FloatingActionButton fab;

    /** Linear Layout for Check In */
    LinearLayout llCheckIn;

    /** Linear Layout for Check Out */
    LinearLayout llCheckOut;

    /** TextView for Check In Date */
    TextView tvCheckIn;

    /** TextView for Check Out Date */
    TextView tvCheckOut;

    DatePickerDialog dpd;

    /** Set Toolbar (Action Bar) */
    Toolbar toolbar;

    int mYear;
    int mMonth;
    int mDay;
    Calendar c;
    String dayOfWeek;
    String dayOfWeek1;

    int mYear1;
    int mMonth1;
    int mDay1;

    SimpleDateFormat simpledateformat;
    Date date;
    Date date1;
    Date fromDate;
    Date newDate;

    String[] MONTHS = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        // Assign Toolbar (Action Bar)
        toolbar = (Toolbar) findViewById(R.id.toolbarSearchResult);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fab.setImageDrawable(mShowingBack
                        ? getResources().getDrawable(R.drawable.ic_view_list_white)
                        : getResources().getDrawable(R.drawable.ic_map_white));

                flipCard();

            }
        });

        llCheckIn = (LinearLayout)findViewById(R.id.linear_layout_check_in);
        llCheckOut = (LinearLayout)findViewById(R.id.linear_layout_check_out);

        tvCheckIn = (TextView)findViewById(R.id.textview_check_in);
        tvCheckOut = (TextView)findViewById(R.id.textview_check_out);

        c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        simpledateformat = new SimpleDateFormat("EEE");

        date = new Date(mYear, mMonth, mDay-1);
        date1 = new Date(mYear, mMonth, mDay);
        dayOfWeek = simpledateformat.format(date);
        dayOfWeek1 = simpledateformat.format(date1);

        tvCheckIn.setText(dayOfWeek.toUpperCase() + "," + " " + MONTHS[mMonth]+ " " + (mDay));
        tvCheckOut.setText(dayOfWeek1.toUpperCase() + "," + " " + MONTHS[mMonth]+ " " + (mDay+1));

        llCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(1);

               /*dpd = new DatePickerDialog(SearchResultActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date = new Date(year, monthOfYear, dayOfMonth-1);
                                dayOfWeek = simpledateformat.format(date);

                                tvCheckIn.setText((dayOfWeek).toUpperCase() + "," + " " + MONTHS[monthOfYear] + " " + (dayOfMonth));

                            }
                        }, mYear, mMonth, mDay);
                dpd.show();*/

            }
        });

        llCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(2);

                /*// Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(SearchResultActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
								*//*
								 * startDate.setText(dayOfMonth + "-" +
								 * (monthOfYear + 1) + "-" + year);
								 *//*

                                date = new Date(year, monthOfYear, dayOfMonth-1);
                                dayOfWeek = simpledateformat.format(date);

                                tvCheckOut.setText((dayOfWeek).toUpperCase() + "," + " " + MONTHS[monthOfYear] + " " + (dayOfMonth));

                            }
                        }, mYear, mMonth, mDay);
                dpd.show();*/



            }
        });

        if (savedInstanceState == null) {
            // If there is no saved instance state, add a fragment representing the
            // front of the card to this activity. If there is saved instance state,
            // this fragment will have already been added to the activity.
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.containerMapList, new SearchListMapViewFragment())
                    .commit();

        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);

        }

        // Monitor back stack changes to ensure the action bar shows the appropriate
        // button (either "photo" or "info").
        getFragmentManager().addOnBackStackChangedListener(this);


    }

    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }

        // Flip to the back.

        mShowingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for the back of
        // the card, uses custom animations, and is part of the fragment manager's back stack.

        getFragmentManager()
                .beginTransaction()

                        // Replace the default fragment animations with animator resources representing
                        // rotations when switching to the back of the card, as well as animator
                        // resources representing rotations when flipping back to the front (e.g. when
                        // the system Back button is pressed).
                .setCustomAnimations(
                        R.anim.card_flip_right_in, R.anim.card_flip_right_out,
                        R.anim.card_flip_left_in, R.anim.card_flip_left_out)

                        // Replace any fragments currently in the container view with a fragment
                        // representing the next page (indicated by the just-incremented currentPage
                        // variable).
                .replace(R.id.containerMapList, new SearchListListViewFragment())

                        // Add this transaction to the back stack, allowing users to press Back
                        // to get to the front of the card.
                .addToBackStack(null)

                        // Commit the transaction.
                .commit();


        // Defer an invalidation of the options menu (on modern devices, the action bar). This
        // can't be done immediately because the transaction may not yet be committed. Commits
        // are asynchronous in that they are posted to the main thread's message loop.
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                invalidateOptionsMenu();
            }
        });
    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);

        // When the back stack changes, invalidate the options menu (action bar).
        invalidateOptionsMenu();
    }

    /*@Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 1:
                // create a new DatePickerDialog with values you want to show

                DatePickerDialog da = new DatePickerDialog(this, mDateSetListener,
                        mYear, mMonth, mDay-1);
                *//*Calendar c = Calendar.getInstance();

                c.add(Calendar.DATE,0);
                newDate = c.getTime();*//*
                da.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                return da;
            // create a new TimePickerDialog with values you want to show
            case 2:
                // create a new DatePickerDialog with values you want to show
                DatePickerDialog da1 = new DatePickerDialog(this,
                        mDateSetListener2, mYear1, mMonth2, mDay1+1);

                *//*Calendar c1 = Calendar.getInstance();
                c1.add(Calendar.DATE, 0);
                newDate = c1.getTime();*//*
              //  da1.getDatePicker().setMinDate(newDate.getTime());
                da1.getDatePicker().setMinDate((System.currentTimeMillis() - 1000)+1);
                return da1;

        }
        return null;
    }

    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        // the callback received when the user "sets" the Date in the
        // DatePickerDialog
        public void onDateSet(DatePicker view, int yearSelected,
                              int monthOfYear, int dayOfMonth) {
            mYear1 = yearSelected;
            mMonth1 = monthOfYear + 1;
            mMonth2 = monthOfYear;
            mDay1 = dayOfMonth;

            fromDate = new Date(yearSelected, monthOfYear, dayOfMonth-1);
            dayOfWeek = simpledateformat.format(fromDate);

            tvCheckIn.setText((dayOfWeek).toUpperCase() + "," + " " + MONTHS[monthOfYear] + " " + (dayOfMonth));


        }
    };

    DatePickerDialog.OnDateSetListener mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
        // the callback received when the user "sets" the Date in the
        // DatePickerDialog
        public void onDateSet(DatePicker view, int yearSelected,
                              int monthOfYear, int dayOfMonth) {
            *//*mYear = yearSelected;
            mMonth = monthOfYear + 1;
            mDay = dayOfMonth;*//*

            date = new Date(yearSelected, monthOfYear, dayOfMonth-1);
            dayOfWeek = simpledateformat.format(date);

            tvCheckOut.setText((dayOfWeek).toUpperCase() + "," + " " + MONTHS[monthOfYear] + " " + (dayOfMonth));


        }
    };*/

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 1:
                // set date picker as current date
                DatePickerDialog _date =   new DatePickerDialog(this, datePickerListener, mYear,mMonth,
                        mDay){
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        if (year < mYear)
                            view.updateDate(mYear, mMonth, mDay);

                        if (monthOfYear < mMonth && year == mYear)
                            view.updateDate(mYear, mMonth, mDay);

                        if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                            view.updateDate(mYear, mMonth, mDay);

                    }
                };
                return _date;

            case 2:

                // set date picker as current date
                DatePickerDialog _date1 =   new DatePickerDialog(this, datePickerListener1, mYear1,mMonth1,
                        mDay1){
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        if (year < mYear1)
                            view.updateDate(mYear1, mMonth1, mDay1);

                        if (monthOfYear < mMonth1 && year == mYear1)
                            view.updateDate(mYear1, mMonth1, mDay1);

                        if (dayOfMonth < mDay1 && year == mYear1 && monthOfYear == mMonth1)
                            view.updateDate(mYear1, mMonth1, mDay1);

                    }
                };
                return _date1;

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            mYear1 = selectedYear;
            mMonth1 = selectedMonth;
            mDay1 = selectedDay;

            fromDate = new Date(selectedYear, selectedMonth, selectedDay-1);
            dayOfWeek = simpledateformat.format(fromDate);

            tvCheckIn.setText((dayOfWeek).toUpperCase() + "," + " " + MONTHS[selectedMonth] + " " + (selectedDay));

        }
    };

    private DatePickerDialog.OnDateSetListener datePickerListener1 = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            mYear = selectedYear;
            mMonth = selectedMonth;
            mDay = selectedDay;

            fromDate = new Date(selectedYear, selectedMonth, selectedDay-1);
            dayOfWeek = simpledateformat.format(fromDate);

            tvCheckOut.setText((dayOfWeek).toUpperCase() + "," + " " + MONTHS[selectedMonth] + " " + (selectedDay));

        }
    };

}
