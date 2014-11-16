package com.tp1.Connection;

import java.sql.Time;

public class MatchTime {

    private Time time;
    private int periode;
    
    public MatchTime(Time time, int periode)
    {
        this.setTime(time);
        this.setPeriode(periode);
    }

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

}
