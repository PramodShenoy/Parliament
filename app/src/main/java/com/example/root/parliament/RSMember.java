package com.example.root.parliament;


public class RSMember {

    String name,party,state;

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

    public RSMember(String name, String party, String state) {

        this.name = name;
        this.party = party;
        this.state = state;
    }
}
