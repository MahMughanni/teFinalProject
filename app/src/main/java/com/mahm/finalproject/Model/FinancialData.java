package com.mahm.finalproject.Model;

public class FinancialData {

    private double Creditor, Debit;
    private String Date;
    private double totalBill;

    public FinancialData(double creditor, double debit, String date, double totalBill) {
        Creditor = creditor;
        Debit = debit;
        Date = date;
        this.totalBill = totalBill;
    }

    public double getCreditor() {
        return Creditor;
    }

    public void setCreditor(double creditor) {
        Creditor = creditor;
    }

    public double getDebit() {
        return Debit;
    }

    public void setDebit(double debit) {
        Debit = debit;
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
