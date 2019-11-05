package com.example.wpls;

public class Class {
    int classID;
    String classCampus;
    int classYear ;
    String classTerm ;
    String classGrade;
    String classTitle;
    String classRoom ;
    String classTime ;
    int classMax ;
    String classFee ;
    String classContact;
    int classStudents;
    int classCredit;



    public Class(int classID,String classTitle,  String classGrade, String classRoom, int classMax, String classTime) {
        this.classID = classID;
        this.classGrade = classGrade;
        this.classTitle = classTitle;
        this.classRoom = classRoom;
        this.classTime = classTime;
        this.classMax = classMax;
    }


    public Class(int classID, String classTitle, String classTime, int classStudents, int classMax) {
        this.classID = classID;
        this.classTitle = classTitle;
        this.classTime = classTime;
        this.classMax = classMax;
        this.classStudents = classStudents;
    }

    public Class(int classID, String classTitle, String classTime, int classStudents, int classMax, int classCredit) {
        this.classID = classID;
        this.classTitle = classTitle;
        this.classTime = classTime;
        this.classMax = classMax;
        this.classStudents = classStudents;
        this.classCredit = classCredit;
    }

    public Class(int classID, String classCampus, int classYear, String classTerm, String classGrade, String classTitle, String classRoom, String classTime, int classMax, String classFee, String classContact, int classCredit) {
        this.classID = classID;
        this.classCampus = classCampus;
        this.classYear = classYear;
        this.classTerm = classTerm;
        this.classGrade = classGrade;
        this.classTitle = classTitle;
        this.classRoom = classRoom;
        this.classTime = classTime;
        this.classMax = classMax;
        this.classFee = classFee;
        this.classContact = classContact;
        this.classCredit = classCredit;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassCampus() {
        return classCampus;
    }

    public void setClassCampus(String classCampus) {
        this.classCampus = classCampus;
    }

    public int getClassYear() {
        return classYear;
    }

    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }

    public String getClassTerm() {
        return classTerm;
    }

    public void setClassTerm(String classTerm) {
        this.classTerm = classTerm;
    }

    public String getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(String classGrade) {
        this.classGrade = classGrade;
    }

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public int getClassMax() {
        return classMax;
    }

    public void setClassMax(int classMax) {
        this.classMax = classMax;
    }

    public String getClassFee() {
        return classFee;
    }

    public void setClassFee(String classFee) {
        this.classFee = classFee;
    }

    public String getClassContact() {
        return classContact;
    }

    public void setClassContact(String classContact) {
        this.classContact = classContact;
    }

    public int getClassStudents() {
        return classStudents;
    }

    public void setClassStudents(int classStudents) {
        this.classStudents = classStudents;
    }

    public int getClassCredit() {
        return classCredit;
    }

    public void setClassCredit(int classCredit) {
        this.classCredit = classCredit;
    }
}
