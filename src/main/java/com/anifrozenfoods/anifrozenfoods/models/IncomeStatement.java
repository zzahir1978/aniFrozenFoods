package com.anifrozenfoods.anifrozenfoods.models;

import java.util.Date;
import java.text.DecimalFormat;
import jakarta.persistence.*;

@Entity
@Table(name = "incomeStatements")
public class IncomeStatement {

    /**
     *
     */
    private static final String currencyFormat = "#,###.00";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String year;
    private String month;
    private double basicSalary;
    private double totalClaim;
    private double epfEmployee;
    private double epfEmployer;
    private double totalEpf;
    private double socso;
    private double eis;
    private double sportClubFee;
    private double nettSalary;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getFormattedBasicSalary() {
        DecimalFormat decimalFormat = new DecimalFormat(currencyFormat);
        return decimalFormat.format(basicSalary);
    }

    public double getTotalClaim() {
        return totalClaim;
    }

    public void setTotalClaim(double totalClaim) {
        this.totalClaim = totalClaim;
    }

    public String getFormattedTotalClaim() {
        DecimalFormat decimalFormat = new DecimalFormat(currencyFormat);
        return decimalFormat.format(totalClaim);
    }

    public double getEpfEmployee() {
        return epfEmployee;
    }

    public void setEpfEmployee(double epfEmployee) {
        this.epfEmployee = epfEmployee;
    }

    public String getFormattedEpfEmployee() {
        DecimalFormat decimalFormat = new DecimalFormat(currencyFormat);
        return decimalFormat.format(epfEmployee);
    }

    public double getEpfEmployer() {
        return epfEmployer;
    }

    public void setEpfEmployer(double epfEmployer) {
        this.epfEmployer = epfEmployer;
    }

    public String getFormattedEpfEmployer() {
        DecimalFormat decimalFormat = new DecimalFormat(currencyFormat);
        return decimalFormat.format(epfEmployer);
    }

    public double getTotalEpf() {
        return totalEpf;
    }

    public void setTotalEpf(double totalEpf) {
        this.totalEpf = totalEpf;
    }

    public String getFormattedTotalEpf() {
        DecimalFormat decimalFormat = new DecimalFormat(currencyFormat);
        return decimalFormat.format(totalEpf);
    }

    public double getSocso() {
        return socso;
    }

    public void setSocso(double socso) {
        this.socso = socso;
    }

    public String getFormattedSocso() {
        DecimalFormat decimalFormat = new DecimalFormat(currencyFormat);
        return decimalFormat.format(socso);
    }

    public double getEis() {
        return eis;
    }

    public void setEis(double eis) {
        this.eis = eis;
    }

    public String getFormattedEis() {
        DecimalFormat decimalFormat = new DecimalFormat(currencyFormat);
        return decimalFormat.format(eis);
    }

    public double getSportClubFee() {
        return sportClubFee;
    }

    public void setSportClubFee(double sportClubFee) {
        this.sportClubFee = sportClubFee;
    }

    public String getFormattedSportClubFee() {
        DecimalFormat decimalFormat = new DecimalFormat(currencyFormat);
        return decimalFormat.format(sportClubFee);
    }

    public double getNettSalary() {
        return nettSalary;
    }

    public void setNettSalary(double nettSalary) {
        this.nettSalary = nettSalary;
    }

    public String getFormattedNettSalary() {
        DecimalFormat decimalFormat = new DecimalFormat(currencyFormat);
        return decimalFormat.format(nettSalary);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
