package com.example.homeenvironment.ApiClasses;

import com.example.homeenvironment.PollutionRetriever;


public class Data {
    private String idx, aqi, dominentpol;
    private PollutionRetriever.Attributions[] attributions;
    private PollutionRetriever.City city;
    private PollutionRetriever.Iaqi iaqi;
    private PollutionRetriever.Time time;
    private PollutionRetriever.Debug debug;

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public PollutionRetriever.Attributions[] getAttributions() {
        return attributions;
    }

    public void setAttributions(PollutionRetriever.Attributions[] attributions) {
        this.attributions = attributions;
    }

    public PollutionRetriever.City getCity() {
        return city;
    }

    public void setCity(PollutionRetriever.City city) {
        this.city = city;
    }

    public String getDominentpol() {
        return dominentpol;
    }

    public void setDominentpol(String dominentpol) {
        this.dominentpol = dominentpol;
    }

    public PollutionRetriever.Iaqi getIaqi() {
        return iaqi;
    }

    public void setIaqi(PollutionRetriever.Iaqi iaqi) {
        this.iaqi = iaqi;
    }

    public PollutionRetriever.Time getTime() {
        return time;
    }

    public void setTime(PollutionRetriever.Time time) {
        this.time = time;
    }

    public PollutionRetriever.Debug getDebug() {
        return debug;
    }

    public void setDebug(PollutionRetriever.Debug debug) {
        this.debug = debug;
    }
}