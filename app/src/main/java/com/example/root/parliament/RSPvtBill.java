package com.example.root.parliament;


public class RSPvtBill {

    String year,title,category,name,ministry;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinistry() {
        return ministry;
    }

    public void setMinistry(String ministry) {
        this.ministry = ministry;
    }

    public RSPvtBill(String year, String title, String name, String ministry, String category) {

        this.title = title;
        this.year = year;
        this.category = category;
        this.name = name;
        this.ministry = ministry;
    }
}
