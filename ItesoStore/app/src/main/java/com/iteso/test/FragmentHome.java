package com.iteso.test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.test.beans.ItemProduct;
import com.iteso.test.database.DataBaseHandler;
import com.iteso.test.database.ItemProductControl;

import java.util.ArrayList;

public class FragmentHome extends Fragment {

    private AdapterProduct mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ItemProduct> myDataSet;
    private RecyclerView recyclerView;

    public ItemProductControl itemProductControl = new ItemProductControl();

    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView = view.findViewById(R.id.fragment_home_recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        DataBaseHandler dh = DataBaseHandler.getInstance(getActivity());
        myDataSet = itemProductControl.getItemProductsByCategory(2, dh);
        mAdapter = new AdapterProduct(getActivity(), myDataSet);
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    public void updateList()  {
        DataBaseHandler dh = DataBaseHandler.getInstance(getActivity());
        myDataSet = itemProductControl.getItemProductsByCategory(2, dh);
        mAdapter = new AdapterProduct(getActivity(), myDataSet);
        recyclerView.setAdapter(mAdapter);
    }
}
