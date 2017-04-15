package com.example.root.parliament;

/**
 * Created by MAHE on 09-Apr-17.
 */

public class TimeLost {
    String session;
    String disruption;
    String sitting_hour;
    String raised;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getDisruption() {
        return disruption;
    }

    public void setDisruption(String disruption) {
        this.disruption = disruption;
    }

    public String getSitting_hour() {
        return sitting_hour;
    }

    public void setSitting_hour(String sitting_hour) {
        this.sitting_hour = sitting_hour;
    }

    public String getRaised() {
        return raised;
    }

    public void setRaised(String raised) {
        this.raised = raised;
    }

    public TimeLost(String session, String sitting_hour, String disruption, String raised) {

        this.session = session;
        this.disruption = disruption;
        this.sitting_hour = sitting_hour;
        this.raised = raised;
    }

}
