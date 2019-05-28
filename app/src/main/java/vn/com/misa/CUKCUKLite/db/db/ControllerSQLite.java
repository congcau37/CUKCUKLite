package vn.com.misa.CUKCUKLite.db.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.CUKCUKLite.model.Food;
import vn.com.misa.CUKCUKLite.model.Unit;

/**
 * Lớp chứa các phương thức truy vấn
 * @Create_by: trand
 * @Date: 5/28/2019
 */
public class ControllerSQLite extends DBOpenHeplper {

    public ControllerSQLite(Context context) {
        super(context);
    }

    /**
     * Hàm lấy danh sách món ăn trong thực đơn
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return: List<Food>
     */
    public List<Food> getFoodFromDatabase() {
        List<Food> foodList = new ArrayList<>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * from food", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int foodID = cursor.getInt(cursor.getColumnIndex("foodID"));
                String foodName = cursor.getString(cursor.getColumnIndex("foodName"));
                int foodPrice = cursor.getInt(cursor.getColumnIndex("foodPrice"));
                int unitID = cursor.getInt(cursor.getColumnIndex("unitID"));
                String colorBackground = cursor.getString(cursor.getColumnIndex("colorBackground"));
                String foodIcon = cursor.getString(cursor.getColumnIndex("foodIcon"));
                String foodStatus = cursor.getString(cursor.getColumnIndex("foodStatus"));
                foodList.add(new Food(foodID, foodName, foodPrice, unitID, colorBackground, foodIcon, foodStatus));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodList;
    }

    /**
     * Hàm lấy danh sách đơn vị
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return: List<Unit>
     */
    public List<Unit> getUnitFromDatabase() {
        List<Unit> unitList = new ArrayList<>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * from unit", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String unitName = cursor.getString(cursor.getColumnIndex("unitName"));
                int unitID = cursor.getInt(cursor.getColumnIndex("unitID"));
                unitList.add(new Unit(unitName, unitID));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unitList;
    }

    /**
     * Hàm thêm mới đơn vị tính
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: newUnitName
     * @Return: result
     */
    public boolean saveNewUnit(String newUnitName) {
        boolean result = false;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("unitName", newUnitName);
            long rs = db.insert("unit", null, values);
            if (rs > 0) {
                result = true;
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Hàm cập nhật đơn vị tính
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: unit
     * @Return: result
     */
    public boolean updateUnit(Unit unit) {
        boolean result = false;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            String unitName = unit.getUnitName();
            int unitID = unit.getUnitID();
            values.put("unitName", unitName);
            values.put("unitID", unitID);
            long rs = db.update("unit", values, "unitID" + "=" + unitID, null);
            if (rs > 0) {
                result = true;
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Hàm lấy ra mã ID đơn vị tính
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: newUnitName
     * @Return: unitID
     */
    public int getUnitID(String newUnitName) {
        int unitID = 0;
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM unit WHERE unitName= '" + newUnitName + "'", null);
            if (cursor != null) {
                cursor.moveToFirst();
                unitID = cursor.getInt(cursor.getColumnIndex("unitID"));
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unitID;
    }

    /**
     * Hàm lấy ra đơn vị dựa theo ID
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: unitID
     * @Return: unit
     */
    public Unit getUnit(int unitID) {
        Unit unit = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM unit WHERE unitID = '" + unitID + "'", null);
            if (cursor != null) {
                cursor.moveToFirst();
                unit = new Unit(cursor.getString(cursor.getColumnIndex("unitName")), unitID);
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unit;
    }

    /**
     * Hàm kiểm tra đơn vị tính đã được sử dụng trong bảng food hay chưa
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: unitID
     * @Return: boolean
     */
    public boolean checkUnit(int unitID) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM food WHERE unitID = '" + unitID + "'", null);
            if (cursor.getCount() > 0) {
                return true;
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Hàm xóa đơn vị tính
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: unitID
     * @Return: boolean
     */
    public boolean deleteUnit(int unitID) {
        try {
            if (checkUnit(unitID) == false) {
                try {
                    SQLiteDatabase db = getWritableDatabase();
                    long rs = db.delete("unit", "unitID=" + unitID, null);
                    if (rs > 0) {
                        return true;
                    }
                    db.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Hàm thêm mới món ăn
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: newFood
     * @Return: result
     */
    public boolean saveNewFood(Food newFood) {
        boolean result = false;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            String foodName = newFood.getFoodName();
            long foodPrice = newFood.getFoodPrice();
            int unitID = newFood.getUnitID();
            String colorBackground = newFood.getColorBackground();
            String foodIcon = newFood.getFoodIcon();
            String foodStatus = newFood.getFoodStatus();
            values.put("foodName", foodName);
            values.put("foodPrice", foodPrice);
            values.put("unitID", unitID);
            values.put("colorBackground", colorBackground);
            values.put("foodIcon", foodIcon);
            values.put("foodStatus", foodStatus);
            long rs = db.insert("food", null, values);
            if (rs > 0) {
                result = true;
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Hàm cập nhật món ăn
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: food
     * @Return: result
     */
    public boolean updateFood(Food food) {
        boolean result = false;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            int foodID = food.getFoodID();
            String foodName = food.getFoodName();
            long foodPrice = food.getFoodPrice();
            int unitID = food.getUnitID();
            String colorBackground = food.getColorBackground();
            String foodIcon = food.getFoodIcon();
            String foodStatus = food.getFoodStatus();
            values.put("foodID", foodID);
            values.put("foodName", foodName);
            values.put("foodPrice", foodPrice);
            values.put("unitID", unitID);
            values.put("colorBackground", colorBackground);
            values.put("foodIcon", foodIcon);
            values.put("foodStatus", foodStatus);
            long rs = db.update("food", values, "foodID" + "=" + foodID, null);
            if (rs > 0) {
                result = true;
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
