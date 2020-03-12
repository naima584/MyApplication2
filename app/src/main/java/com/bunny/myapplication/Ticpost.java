package com.bunny.myapplication;

public class Ticpost {
    private String ticketType;
    private String ticketQuantity;
    private String vehicleName;
    private String ticketDate;
    private String ticketPrice;
    private String ticketContact;
    private String ticpostId;

    public Ticpost() {
    }

    public Ticpost(String ticketType, String ticketQuantity, String vehicleName, String ticketDate, String ticketPrice, String ticketContact, String ticpostId) {
        this.ticketType = ticketType;
        this.ticketQuantity = ticketQuantity;
        this.vehicleName = vehicleName;
        this.ticketDate = ticketDate;
        this.ticketPrice = ticketPrice;
        this.ticketContact = ticketContact;
        this.ticpostId = ticpostId;
    }

    public String getTicketType() {
        return ticketType;
    }

    public String getTicketQuantity() {
        return ticketQuantity;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public String getTicketContact() {
        return ticketContact;
    }

    public String getTicpostId() {
        return ticpostId;
    }

    public void setTicpostId(String ticpostId) {
        this.ticpostId = ticpostId;
    }
}
