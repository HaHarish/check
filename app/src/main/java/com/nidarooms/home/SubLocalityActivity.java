package com.nidarooms.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nidarooms.R;
import com.nidarooms.searchresult.SearchResultActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * SubLocality Activity to display list of Sub Areas against choosed City in previous screen.
 *
 * @author Harish
 * @version 1.0 04/11/15
 */
public class SubLocalityActivity extends AppCompatActivity {

    // Set Toolbar (Action Bar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_locality);

        // Assign Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarSubLocality);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SubLocalityActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        // Set Title for Action Bar
        getSupportActionBar().setTitle("Choose Locality");

        final ListView listview = (ListView) findViewById(R.id.listview_sub_locality);
        String[] values = new String[] { "Jakarta", "Bandung", "Surabaya",
                "Medan", "Banda Aceh", "Padang", "Palembang", "Batam",
                "Samarinda", "Makassar", "Semarang", "Balikpapan", "Tangerang", "Manado"};

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });

                Intent intent = new Intent(SubLocalityActivity.this, SearchResultActivity.class);
                startActivity(intent);
            }

        });
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
