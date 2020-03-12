package com.bunny.myapplication;

public class Emergencypost {

    private String helpType;
    private String helpQuantity;
    private String helpBuild;
    private String helpFloor;
    private String helpContact;
    private  String emergencypostId;

    public Emergencypost() {
    }

    public Emergencypost(String helpType, String helpQuantity, String helpBuild, String helpFloor, String helpContact, String emergencypostId) {
        this.helpType = helpType;
        this.helpQuantity = helpQuantity;
        this.helpBuild = helpBuild;
        this.helpFloor = helpFloor;
        this.helpContact = helpContact;
        this.emergencypostId = emergencypostId;
    }

    public String getHelpType() {
        return helpType;
    }

    public String getHelpQuantity() {
        return helpQuantity;
    }

    public String getHelpBuild() {
        return helpBuild;
    }

    public String getHelpFloor() {
        return helpFloor;
    }

    public String getHelpContact() {
        return helpContact;
    }

    public String getEmergencypostId() {
        return emergencypostId;
    }

    public void setEmergencypostId(String emergencypostId) {
        this.emergencypostId = emergencypostId;
    }
}
