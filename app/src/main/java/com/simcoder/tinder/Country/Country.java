package com.simcoder.tinder.Country;

import java.util.List;

public class Country {

    public List<String> countries;

    private String countryName;
    private int countryId;

    public Country (String countryName, int countryId) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public String getCountryName(){
        return countryName;
    }
    public void setCountryName(String countryName){
        this.countryName = countryName;
    }

    public int getCountryId(){
        return countryId;
    }
    public void setCountryId(int countryId){
        this.countryId = countryId;
    }

    }
