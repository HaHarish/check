package com.nidarooms.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nidarooms.R;
import com.nidarooms.baseadapter.HotelsListAdapter;
import com.nidarooms.searchresult.HotelsDetailsActivity;

/**
 * Search ListView Fragment to display Hotels List.
 *
 * @author Harish
 * @version 1.0 04/11/15
 */
public class SearchListListViewFragment extends Fragment {

    RecyclerView mRecyclerView;
    StaggeredGridLayoutManager mStaggeredLayoutManager;
    HotelsListAdapter mAdapter;

    public SearchListListViewFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_view,container,false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_list_hotels);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        mAdapter = new HotelsListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        HotelsListAdapter.OnItemClickListener onItemClickListener = new HotelsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(getActivity(), "Clicked " + position, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), HotelsDetailsActivity.class);
                intent.putExtra(HotelsDetailsActivity.EXTRA_PARAM_ID, position);
                startActivity(intent);
            }
        };

        mAdapter.setOnItemClickListener(onItemClickListener);

        return v;
    }
}
