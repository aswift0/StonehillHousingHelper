package com.hfad.stonehillhousinghelper;

import java.util.ArrayList;

public class Posting {
    private String name;
    private String description;
    private String requesting;
    private String location;
    private String cellNumber;
    private String year;
    //public static ArrayList<Posting> theposts = new ArrayList<Posting>();
    public static ArrayList<Posting> theposts = new ArrayList<Posting>();
    public Posting(String name, String location, String requesting, String cellNumber, String description, String year) {
        this.name = name;
        this.requesting = requesting;
        //upload a picture
        this.location = location;
        this.cellNumber = cellNumber;
        this.description = description;
        this.year = year;
    }
    public String getYear() { return year; }
    public String getDescription() { return description; }
    public String getName() {
        return name;
    }
    public String getRequesting() {
        return requesting;
    }
    public String getLocation() {
        return location;
    }
    public String getCellNumber() { return cellNumber;}
    public boolean addPost(String nam, String locatio, String requestin, String cellNumbe, String descriptio, String years){
        if((!nam.isEmpty())&&(!locatio.isEmpty())&&(!requestin.isEmpty())&&(!cellNumbe.isEmpty())&&(!descriptio.isEmpty())&&(!years.isEmpty())) {
            Posting temp = new Posting(nam, locatio, requestin, cellNumbe, descriptio,years);
            theposts.add(temp);
            return true;
        }
        return false;
    }
    public String toString() {
        return this.name + this.requesting;
    }
}