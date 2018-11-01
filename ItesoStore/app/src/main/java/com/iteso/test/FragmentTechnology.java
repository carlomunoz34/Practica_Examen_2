package com.iteso.test;

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

        /*if(myDataSet == null){
            myDataSet = new ArrayList<>();
            ItemProduct mac = new ItemProduct();
            mac.setTitle(getString(R.string.mac_title));
            mac.setStore(getString(R.string.mac_store));
            mac.setLocation(getString(R.string.mac_location));
            mac.setPhone(getString(R.string.mac_phone));
            mac.setImage(0);
            mac.setCode(0);
            mac.setDescription(getString(R.string.mac_Description));
            myDataSet.add(mac);


            ItemProduct alienware = new ItemProduct();
            alienware.setTitle(getString(R.string.aw_title));
            alienware.setStore(getString(R.string.aw_store));
            alienware.setLocation(getString(R.string.mac_location));
            alienware.setPhone(getString(R.string.aw_phone));
            alienware.setImage(1);
            alienware.setCode(1);
            alienware.setDescription(getString(R.string.aw_Description));
            myDataSet.add(alienware);
        }*/
        DataBaseHandler dh = DataBaseHandler.getInstance(getActivity());
        myDataSet = itemProductControl.getItemProductsByCategory(1, dh);

        mAdapter = new AdapterProduct(getActivity(), myDataSet);
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    public void modifyItem(ItemProduct itemProduct, int index) {
        myDataSet.set(index, itemProduct);
        mAdapter = new AdapterProduct(getActivity(), myDataSet);
        recyclerView.setAdapter(mAdapter);
    }
}
