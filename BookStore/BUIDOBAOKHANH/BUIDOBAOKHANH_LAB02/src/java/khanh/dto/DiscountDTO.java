/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.dto;

/**
 *
 * @author buido
 */
public class DiscountDTO {

    private String discountCode, description, importDate;
    private int percent;

    public DiscountDTO(String discountCode, String description, String importDate, int percent) {
        this.discountCode = discountCode;
        this.description = description;
        this.importDate = importDate;
        this.percent = percent;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
