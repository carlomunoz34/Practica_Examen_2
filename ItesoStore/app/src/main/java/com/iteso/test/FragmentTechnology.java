package com.iteso.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.test.beans.Category;
import com.iteso.test.beans.City;
import com.iteso.test.beans.ItemProduct;
import com.iteso.test.beans.Store;
import com.iteso.test.database.DataBaseHandler;
import com.iteso.test.database.ItemProductControl;

import java.util.ArrayList;


public class FragmentTechnology extends Fragment {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ItemProduct> myDataSet;
    private RecyclerView recyclerView;

    public ItemProductControl itemProductControl = new ItemProductControl();

    public FragmentTechnology() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_technology,container,false);
        recyclerView = view.findViewById(R.id.fragment_technology_recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        DataBaseHandler dh = DataBaseHandler.getInstance(getActivity());
        myDataSet = itemProductControl.getItemProductsByCategory(1, dh);
        mAdapter = new AdapterProduct(getActivity(), myDataSet);
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    public void updateList()  {
        DataBaseHandler dh = DataBaseHandler.getInstance(getActivity());
        myDataSet = itemProductControl.getItemProductsByCategory(1, dh);
        mAdapter = new AdapterProduct(getActivity(), myDataSet);
        recyclerView.setAdapter(mAdapter);
    }
}
