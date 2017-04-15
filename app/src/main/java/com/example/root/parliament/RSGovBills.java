package com.example.root.parliament;


public class RSGovBills {

    String year;
    String title,ministry,category;

    public void setYear(String year) {
        this.year = year;
    }

    public void setMinistry(String ministry) {
        this.ministry = ministry;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public RSGovBills() {

    }

    public RSGovBills(String year, String title, String ministry, String category) {

        this.year = year;
        this.title = title;
        this.ministry = ministry;
        this.category = category;
    }

    public String getYear() {

        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getMinistry() {
        return ministry;
    }

    public String getCategory() {
        return category;
    }
}
