package vn.com.misa.CUKCUKLite.model;

import java.io.Serializable;

/**
 * Lớp món ăn
 * @created_by tdcong
 * @date 5/20/2019
 */
public class Dish implements Serializable {
    int dishID;
    String dishName;
    long dishPrice;
    int unitID;
    String colorBackground;
    String dishIcon;
    String dishStatus;

    public Dish() {
    }

    public Dish(int dishID, String dishName, long dishPrice, int unitID, String colorBackground, String dishIcon, String dishStatus) {
        this.dishID = dishID;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.unitID = unitID;
        this.colorBackground = colorBackground;
        this.dishIcon = dishIcon;
        this.dishStatus = dishStatus;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public long getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(long dishPrice) {
        this.dishPrice = dishPrice;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }

    public String getColorBackground() {
        return colorBackground;
    }

    public void setColorBackground(String colorBackground) {
        this.colorBackground = colorBackground;
    }

    public String getDishIcon() {
        return dishIcon;
    }

    public void setDishIcon(String dishIcon) {
        this.dishIcon = dishIcon;
    }

    public String getDishStatus() {
        return dishStatus;
    }

    public void setDishStatus(String dishStatus) {
        this.dishStatus = dishStatus;
    }
}
