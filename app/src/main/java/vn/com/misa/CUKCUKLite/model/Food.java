package vn.com.misa.CUKCUKLite.model;

import java.io.Serializable;

/**
 * Lớp món ăn
 * @created_by tdcong
 * @date 5/20/2019
 */
public class Food implements Serializable {
    int foodID;
    String foodName;
    long foodPrice;
    int unitID;
    String colorBackground;
    String foodIcon;
    String foodStatus;

    public Food(int foodID, String foodName, long foodPrice, int unitID, String colorBackground, String foodIcon, String foodStatus) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.unitID = unitID;
        this.colorBackground = colorBackground;
        this.foodIcon = foodIcon;
        this.foodStatus = foodStatus;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public long getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(long foodPrice) {
        this.foodPrice = foodPrice;
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

    public String getFoodIcon() {
        return foodIcon;
    }

    public void setFoodIcon(String foodIcon) {
        this.foodIcon = foodIcon;
    }

    public String getFoodStatus() {
        return foodStatus;
    }

    public void setFoodStatus(String foodStatus) {
        this.foodStatus = foodStatus;
    }
}
