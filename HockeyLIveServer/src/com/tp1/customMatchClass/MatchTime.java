package com.tp1.customMatchClass;

import java.sql.Time;

public class MatchTime {

    private Time time;
    private String periode;
    
    public MatchTime(Time time, String periode)
    {
        this.setTime(time);
        this.setPeriode(periode);
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

}
