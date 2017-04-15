package com.example.root.parliament;

public class Attendance {

    String seat;
    String name;
    String state,constituency;
    String signed;

    public Attendance(String seat, String name, String state, String constituency, String signed) {
        this.seat = seat;
        this.name = name;
        this.state = state;
        this.constituency = constituency;
        this.signed = signed;
    }
    public Attendance()
    {

    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public String getSigned() {
        return signed;
    }

    public void setSigned(String signed) {
        this.signed = signed;
    }
}
