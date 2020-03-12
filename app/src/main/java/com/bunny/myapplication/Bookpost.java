package com.bunny.myapplication;

public class Bookpost {
    private String bookName;
    private String bookQuantity;
    private String bookWritter;
    private String bookEdition;
    private String bookPrice;
    private  String bookpostId;

    public Bookpost() {
    }

    public Bookpost(String bookName, String bookQuantity, String bookWritter, String bookEdition, String bookPrice, String bookpostId) {
        this.bookName = bookName;
        this.bookQuantity = bookQuantity;
        this.bookWritter = bookWritter;
        this.bookEdition = bookEdition;
        this.bookPrice = bookPrice;
        this.bookpostId = bookpostId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookQuantity() {
        return bookQuantity;
    }

    public String getBookWritter() {
        return bookWritter;
    }

    public String getBookEdition() {
        return bookEdition;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public String getBookpostId() {
        return bookpostId;
    }

    public void setBookpostId(String bookpostId) {
        this.bookpostId = bookpostId;
    }
}
