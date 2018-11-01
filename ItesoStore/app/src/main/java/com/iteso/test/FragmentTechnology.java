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

        /*Store guadalajaraStore = new Store(1,"Galerías","3333960825",
                1,20.4500,-103.1600, new City(1, "Guadalajara"));
        Store cdmxStore = new Store(2,"Antara","5557964852",
                2,19.2542,-99.0739, new City(2, "CDMX"));
        Store monterreyStore = new Store(3,"Nuevo Sur","9603333825",
                3,25.6714,-100.3040, new City(3, "Monterrey"));


        /////  PRUEBAS
        ItemProduct itemProduct1 = new ItemProduct(1, "Laptop 1", 1, guadalajaraStore, new Category(1, "Technology"));
        ItemProduct itemProduct2 = new ItemProduct(2, "Laptop 1", 1, monterreyStore, new Category(1, "Technology"));
        ItemProduct itemProduct3 = new ItemProduct(3, "Laptop 2", 2, guadalajaraStore, new Category(1, "Technology"));
        itemProductControl.addItemProduct(itemProduct1,dh);
        itemProductControl.addItemProduct(itemProduct2,dh);
        itemProductControl.addItemProduct(itemProduct3,dh);*/

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
