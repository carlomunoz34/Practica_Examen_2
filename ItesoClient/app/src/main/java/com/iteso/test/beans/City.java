package com.iteso.test.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {
    private int id;
    private String name;

    public City(){
        setId(0);
        setName("");
    }

    public City(int id, String name){
        setId(id);
        setName(name);
    }

    protected City(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    //Getters & Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}
