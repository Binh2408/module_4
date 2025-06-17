package org.example.quan_ly_khach_hang.model;

public class Customer {
    private int idCus;
    private String nameCus;
    private String emailCus;
    private String addressCus;

    public Customer() {
    }

    public Customer(int idCus, String nameCus, String emailCus, String addressCus) {
        this.idCus = idCus;
        this.nameCus = nameCus;
        this.emailCus = emailCus;
        this.addressCus = addressCus;
    }

    public int getIdCus() {
        return idCus;
    }

    public void setIdCus(int idCus) {
        this.idCus = idCus;
    }

    public String getNameCus() {
        return nameCus;
    }

    public void setNameCus(String nameCus) {
        this.nameCus = nameCus;
    }

    public String getEmailCus() {
        return emailCus;
    }

    public void setEmailCus(String emailCus) {
        this.emailCus = emailCus;
    }

    public String getAddressCus() {
        return addressCus;
    }

    public void setAddressCus(String addressCus) {
        this.addressCus = addressCus;
    }
}
