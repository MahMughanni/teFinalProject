package com.ucas.graduationproject.Model;

public class FinancialData {

    private double Creditor;
    private String Date;
    private double totalBill;

    public FinancialData(double creditor, String date, double totalBill) {
        Creditor = creditor;
        Date = date;
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
}
