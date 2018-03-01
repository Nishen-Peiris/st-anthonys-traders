package com.nishenpeiris.StockManagementSystem;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

@Entity
public class Invoice extends AbstractEntity {
    private String invoiceNumber;
    private String issueNumber;
    private String orderNumber;
    private String vehicleNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private Driver driver;
    private double subTotal;
    private double grandTotal;

    public Invoice(String invoiceNumber, String issueNumber, String orderNumber, String vehicleNumber, Driver driver, double subTotal, double grandTotal) {
        this.invoiceNumber = invoiceNumber;
        this.issueNumber = issueNumber;
        this.orderNumber = orderNumber;
        this.vehicleNumber = vehicleNumber;
        this.driver = driver;
        this.subTotal = subTotal;
        this.grandTotal = grandTotal;
    }

    protected Invoice() {

    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }
}