package com.ridoy.covid19tracker.API;

public class UserCountry {
    String country,countryCode;

    public UserCountry(String country, String countryCode) {
        this.country = country;
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
