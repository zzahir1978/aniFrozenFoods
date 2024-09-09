package com.anifrozenfoods.anifrozenfoods.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class ExpenseDto {

    @NotEmpty(message = "The date is required")
    private String date;

    @NotEmpty(message = "The description is required")
    private String description;

    @NotEmpty(message = "The item is required")
    private String item;

    @NotEmpty(message = "The qty is required")
    private @NotEmpty(message = "The qty is required") String qty;

    @Min(0)
    private @Min(0) double unitRate;

    @Min(0)
    private @Min(0) double subTotal;

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

    public @NotEmpty(message = "The qty is required") String getQty() {
        return qty;
    }

    public void setQty(String string) {
        this.qty = string;
    }
    public @Min(0) double getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(double d) {
        this.unitRate = d;
    }

    public @Min(0) double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double d) {
        this.subTotal = d;
    }

}
