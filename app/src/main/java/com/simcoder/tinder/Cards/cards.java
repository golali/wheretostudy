package com.simcoder.tinder.Cards;

import java.util.List;

/**
 * Created by manel on 9/5/2017.
 */

public class cards {
    public List<String> countries;
    private String countryImageUrl;

    private String userId;
    private String name;
    private String profileImageUrl;
    public cards (String userId, String name, String profileImageUrl, List<String> countries) {
        this.userId = userId;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.countries = countries;
    }

    public String getUserId(){
        return userId;
    }
    public void setUserID(String userID){
        this.userId = userId;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getProfileImageUrl(){
        return profileImageUrl;
    }
    public void setProfileImageUrl(String profileImageUrl){
        this.profileImageUrl = profileImageUrl;
    }
}
