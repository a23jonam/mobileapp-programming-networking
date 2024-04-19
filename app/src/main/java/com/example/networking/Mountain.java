package com.example.networking;

public class Mountain {

    private String name;
    private String location;
    private int height;

    public Mountain(String inName, String inLocation, int inHeight) {
        name=inName;
        location=inLocation;
        height=inHeight;
    }

    public Mountain(String inName) {
        name=inName;
        location="";
        height=-1;
    }

    public String toString() { return name; }

    public String info() {
        String str=name;
        str+=" is located in ";
        str+=location;
        str+=Integer.toString(height);
        str+="m.";
        return str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int newHeight) {
        height=newHeight;
    }
}