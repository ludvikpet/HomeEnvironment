package com.example.homeenvironment.ApiClasses;

import com.example.homeenvironment.PollutionRetriever;

public class Iaqi {
    private PollutionRetriever.Pm25 pm25;
    private PollutionRetriever.Pm10 pm10;
    private Co co;
    private PollutionRetriever.H h;
    private PollutionRetriever.O3 o3;
    private PollutionRetriever.P p;
    private PollutionRetriever.So2 so2;
    private PollutionRetriever.T t;
    private PollutionRetriever.W w;

    public PollutionRetriever.Pm25 getPm25() {
        return pm25;
    }
    public void setPm25(PollutionRetriever.Pm25 pm25) {
        this.pm25 = pm25;
    }

    public PollutionRetriever.Pm10 getPm10() {
        return pm10;
    }

    public void setPm10(PollutionRetriever.Pm10 pm10) {
        this.pm10 = pm10;
    }

    public Co getCo() {
        return co;
    }

    public void setCo(Co co) {
        this.co = co;
    }

    public PollutionRetriever.H getH() {
        return h;
    }

    public void setH(PollutionRetriever.H h) {
        this.h = h;
    }

    public PollutionRetriever.O3 getO3() {
        return o3;
    }

    public void setO3(PollutionRetriever.O3 o3) {
        this.o3 = o3;
    }

    public PollutionRetriever.P getP() {
        return p;
    }

    public void setP(PollutionRetriever.P p) {
        this.p = p;
    }

    public PollutionRetriever.So2 getSo2() {
        return so2;
    }

    public void setSo2(PollutionRetriever.So2 so2) {
        this.so2 = so2;
    }

    public PollutionRetriever.W getW() {
        return w;
    }

    public void setW(PollutionRetriever.W w) {
        this.w = w;
    }

    public PollutionRetriever.T getT() {
        return t;
    }

    public void setT(PollutionRetriever.T t) {
        this.t = t;
    }
}
