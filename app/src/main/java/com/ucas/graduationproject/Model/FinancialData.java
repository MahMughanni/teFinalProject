package com.ucas.graduationproject.Model;

public class FinancialData {

    private double Creditor;
    private String Date, semesterName;
    private double pay;
    private double totalBill;

    public FinancialData(double creditor, String date, String semesterName, double pay, double totalBill) {
        Creditor = creditor;
        Date = date;
        this.semesterName = semesterName;
        this.pay = pay;
        this.totalBill = totalBill;
    }

    public double getCreditor() {
        return Creditor;
    }

    public void setCreditor(double creditor) {
        Creditor = creditor;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }
}
