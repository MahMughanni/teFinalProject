package com.ucas.graduationproject.Model;

public class Student {

    private int id;

    private String name;

    private String studentDateOfBirth;

    private int phoneNumber;

    public Student(int id, String name, String studentDateOfBirth, int phoneNumber) {
        this.id = id;
        this.name = name;
        this.studentDateOfBirth = studentDateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentDateOfBirth() {
        return studentDateOfBirth;
    }

    public void setStudentDateOfBirth(String studentDateOfBirth) {
        this.studentDateOfBirth = studentDateOfBirth;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
