package vn.com.misa.CUKCUKLite.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.CUKCUKLite.model.Dish;
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
     * @Return: List<Dish>
     */
    public List<Dish> getFoodFromDatabase() {
        List<Dish> dishList = new ArrayList<>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * from dish", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int foodID = cursor.getInt(cursor.getColumnIndex("dishID"));
                String foodName = cursor.getString(cursor.getColumnIndex("dishName"));
                int foodPrice = cursor.getInt(cursor.getColumnIndex("dishPrice"));
                int unitID = cursor.getInt(cursor.getColumnIndex("unitID"));
                String colorBackground = cursor.getString(cursor.getColumnIndex("colorBackground"));
                String foodIcon = cursor.getString(cursor.getColumnIndex("dishIcon"));
                String foodStatus = cursor.getString(cursor.getColumnIndex("dishStatus"));
                dishList.add(new Dish(foodID, foodName, foodPrice, unitID, colorBackground, foodIcon, foodStatus));
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dishList;
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
     * @Param: newDish
     * @Return: result
     */
    public boolean saveNewFood(Dish newDish) {
        boolean result = false;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            String foodName = newDish.getDishName();
            long foodPrice = newDish.getDishPrice();
            int unitID = newDish.getUnitID();
            String colorBackground = newDish.getColorBackground();
            String foodIcon = newDish.getDishIcon();
            String foodStatus = newDish.getDishStatus();
            values.put("dishName", foodName);
            values.put("dishPrice", foodPrice);
            values.put("unitID", unitID);
            values.put("colorBackground", colorBackground);
            values.put("dishIcon", foodIcon);
            values.put("dishStatus", foodStatus);
            long rs = db.insert("dish", null, values);
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
     * @Param: dish
     * @Return: result
     */
    public boolean updateFood(Dish dish) {
        boolean result = false;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            int foodID = dish.getDishID();
            String foodName = dish.getDishName();
            long foodPrice = dish.getDishPrice();
            int unitID = dish.getUnitID();
            String colorBackground = dish.getColorBackground();
            String foodIcon = dish.getDishIcon();
            String foodStatus = dish.getDishStatus();
            values.put("dishID", foodID);
            values.put("dishName", foodName);
            values.put("dishPrice", foodPrice);
            values.put("unitID", unitID);
            values.put("colorBackground", colorBackground);
            values.put("dishIcon", foodIcon);
            values.put("dishStatus", foodStatus);
            long rs = db.update("dish", values, "dishID" + "=" + foodID, null);
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
