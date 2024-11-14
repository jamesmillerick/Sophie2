package com.fyp.SpringSophie2.model;

public class Department {
    private String deptName;
    private String username;
    private String fName;
    private String sName;

    public Department(String deptName, String username, String fName, String sName) {
        this.deptName = deptName;
        this.username = username;
        this.fName = fName;
        this.sName = sName;
    }

    public Department() {

    }

    //Getters and Setters for all attributes

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }
}
