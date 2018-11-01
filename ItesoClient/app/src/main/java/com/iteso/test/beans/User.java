package com.iteso.test.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public  String name;
    public  String password;
    public  Boolean isLogged;

    public User(){
        setName("");
        setPassword("");
        setLogged(false);
    }

    public User(String name, String password, Boolean isLogged) {
        setName(name);
        setPassword(password);
        setLogged(isLogged);
    }

    //Getters & Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean isLogged() {
        return isLogged;
    }
    public void setLogged(Boolean logged) {
        isLogged = logged;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", isLogged=" + isLogged +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.password);
        dest.writeValue(this.isLogged);
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.password = in.readString();
        this.isLogged = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
