package com.anifrozenfoods.anifrozenfoods.models;

import java.util.Date;
import java.text.DecimalFormat;
import jakarta.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {

    private static final String currencyFormat = "#,###.00";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String date;
    private String description;
    private String item;
    private String qty;
    private double unitRate;
    private double subTotal;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public double getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(double unitRate) {
        this.unitRate = unitRate;
    }

    public String getFormattedUnitRate() {
        DecimalFormat decimalFormat = new DecimalFormat(currencyFormat);
        return decimalFormat.format(unitRate);
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public String getFormattedSubTotal() {
        DecimalFormat decimalFormat = new DecimalFormat(currencyFormat);
        return decimalFormat.format(subTotal);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
