package com.example.myapplication;

public class item {
    private String title;
    private String facility;
    private String name;
    private String address;
    private String visitTime;
    private String visitTime2;
    private String disinfection;

    public item(String title, String facility, String name, String address, String visitTime,String visitTime2, String disinfection){
        this.title = title;
        this.facility = facility;
        this.name = name;
        this.address = address;
        this.visitTime = visitTime;
        this.visitTime2 = visitTime2;
        this.disinfection = disinfection;
    }

    public String getTitle() {
        return title;
    }
    public String getFacility() {
        return facility;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getVisitTime() {
        return visitTime;
    }
    public String getVisitTime2(){
        return visitTime2;
    }
    public String getDisinfection() {
        return disinfection;
    }

}
