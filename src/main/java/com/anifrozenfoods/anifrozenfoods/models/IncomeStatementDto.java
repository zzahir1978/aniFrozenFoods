package com.anifrozenfoods.anifrozenfoods.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class IncomeStatementDto {

    @NotEmpty(message = "The year is required")
    private String year;

    @NotEmpty(message = "The month is required")
    private String month;

    @Min(0)
    private double basicSalary;

    @Min(0)
    private double totalClaim;

    @Min(0)
    private double epfEmployee;

    @Min(0)
    private double epfEmployer;

    @Min(0)
    private double totalEpf;

    @Min(0)
    private double socso;

    @Min(0)
    private double eis;

    @Min(0)
    private double sportClubFee;

    @Min(0)
    private double nettSalary;

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

    public double getTotalClaim() {
        return totalClaim;
    }

    public void setTotalClaim(double totalClaim) {
        this.totalClaim = totalClaim;
    }

    public double getEpfEmployee() {
        return epfEmployee;
    }

    public void setEpfEmployee(double epfEmployee) {
        this.epfEmployee = epfEmployee;
    }

    public double getEpfEmployer() {
        return epfEmployer;
    }

    public void setEpfEmployer(double epfEmployer) {
        this.epfEmployer = epfEmployer;
    }

    public double getTotalEpf() {
        return totalEpf;
    }

    public void setTotalEpf(double totalEpf) {
        this.totalEpf = totalEpf;
    }

    public double getSocso() {
        return socso;
    }

    public void setSocso(double socso) {
        this.socso = socso;
    }

    public double getEis() {
        return eis;
    }

    public void setEis(double eis) {
        this.eis = eis;
    }

    public double getSportClubFee() {
        return sportClubFee;
    }

    public void setSportClubFee(double sportClubFee) {
        this.sportClubFee = sportClubFee;
    }

    public double getNettSalary() {
        return nettSalary;
    }

    public void setNettSalary(double nettSalary) {
        this.nettSalary = nettSalary;
    }
}
