package vn.com.misa.CUKCUKLite.model;

/**
 * class món ăn
 * @created_by tdcong
 * @date 5/20/2019
 */
public class Food {
    int foodID;
    String foodName;
    int Price;
    int UnitID;
    int Color;
    int Icon;
    String Status;

    public Food(int foodID, String foodName, int price, int unitID, int color, int icon, String status) {
        this.foodID = foodID;
        this.foodName = foodName;
        Price = price;
        UnitID = unitID;
        Color = color;
        Icon = icon;
        Status = status;
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

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getUnitID() {
        return UnitID;
    }

    public void setUnitID(int unitID) {
        UnitID = unitID;
    }

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
