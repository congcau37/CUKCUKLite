package vn.com.misa.CUKCUKLite.model;

/**
 * class món ăn
 * @created_by tdcong
 * @date 5/20/2019
 */
public class Food {
    int foodID;
    String foodName;
    int foodPrice;
    String unitID;
    String colorBackground;
    String foodIcon;
    String foodStatus;

    public Food(int foodID, String foodName, int foodPrice, String unitID, String colorBackground, String foodIcon, String foodStatus) {
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

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getUnitID() {
        return unitID;
    }

    public void setUnitID(String unitID) {
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
