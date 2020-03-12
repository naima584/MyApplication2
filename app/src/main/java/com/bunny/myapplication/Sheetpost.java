package com.bunny.myapplication;

public class Sheetpost {

    private String sheetName;
    private String sheetQuantity;
    private String sheetTopic;
    private String sheetPrice;
    private  String sheetpostId;

    public Sheetpost() {
    }

    public Sheetpost(String sheetName, String sheetQuantity, String sheetTopic, String sheetPrice, String sheetpostId) {
        this.sheetName = sheetName;
        this.sheetQuantity = sheetQuantity;
        this.sheetTopic = sheetTopic;
        this.sheetPrice = sheetPrice;
        this.sheetpostId = sheetpostId;
    }

    public String getSheetName() {
        return sheetName;
    }

    public String getSheetQuantity() {
        return sheetQuantity;
    }

    public String getSheetTopic() {
        return sheetTopic;
    }

    public String getSheetPrice() {
        return sheetPrice;
    }

    public String getSheetpostId() {
        return sheetpostId;
    }

    public void setSheetpostId(String sheetpostId) {
        this.sheetpostId = sheetpostId;
    }
}
