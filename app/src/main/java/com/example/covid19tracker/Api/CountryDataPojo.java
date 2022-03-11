package com.example.covid19tracker.Api;

//import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CountryDataPojo {

    //    @SerializedName("VariableName") If we need to use our custom variable name instead of api variable name
    private String updated, country, cases, active, deaths, recovered, todayCases, todayDeaths, todayRecovered, tests;
    private Map<String, String> countryInfo;

    public CountryDataPojo(String updated, String country, String cases, String active, String deaths, String recovered, String todayCases, String todayDeaths, String todayRecovered, String tests, Map<String, String> countryInfo) {
        this.updated = updated;
        this.country = country;
        this.cases = cases;
        this.active = active;
        this.deaths = deaths;
        this.recovered = recovered;
        this.todayCases = todayCases;
        this.todayDeaths = todayDeaths;
        this.todayRecovered = todayRecovered;
        this.tests = tests;
        this.countryInfo = countryInfo;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public Map<String, String> getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(Map<String, String> countryInfo) {
        this.countryInfo = countryInfo;
    }
}
