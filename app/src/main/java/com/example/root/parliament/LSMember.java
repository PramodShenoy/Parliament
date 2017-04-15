package com.example.root.parliament;

/**
 * Created by MAHE on 09-Apr-17.
 */

public class LSMember {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConsti() {
        return consti;
    }

    public void setConsti(String consti) {
        this.consti = consti;
    }

    String name,party,state,consti;

    public LSMember(String name, String party, String state, String consti) {
        this.name = name;
        this.party = party;
        this.state = state;
        this.consti = consti;
    }
}
