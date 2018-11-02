package com.carlo.itesoclient.beans;

public class Item {
    private String title, store;
    private int category;

    public Item() {
    }

    public Item(String title, int category, String store) {
        this.title = title;
        this.category = category;
        this.store = store;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

}
