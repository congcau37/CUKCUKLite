package vn.com.misa.CUKCUKLite.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.CUKCUKLite.model.Dish;
import vn.com.misa.CUKCUKLite.model.Unit;
import vn.com.misa.CUKCUKLite.util.ConstantKey;

/**
 * Lớp chứa các phương thức truy vấn
 *
 * @Create_by: trand
 * @Date: 5/28/2019
 */
public class ControllerSQLite extends DBOpenHeplper {

    public ControllerSQLite(Context context) {
        super(context);
    }

    /**
     * Hàm lấy danh sách món ăn trong thực đơn
     *
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
                int foodID = cursor.getInt(cursor.getColumnIndex(ConstantKey.COLUMN_NAME_DISH_ID));
                String foodName = cursor.getString(cursor.getColumnIndex(ConstantKey.COLUMN_NAME_DISH_NAME));
                int foodPrice = cursor.getInt(cursor.getColumnIndex(ConstantKey.COLUMN_NAME_DISH_PRICE));
                int unitID = cursor.getInt(cursor.getColumnIndex(ConstantKey.COLUMN_NAME_UNIT_ID));
                String colorBackground = cursor.getString(cursor.getColumnIndex(ConstantKey.COLUMN_NAME_COLOR));
                String foodIcon = cursor.getString(cursor.getColumnIndex(ConstantKey.COLUMN_NAME_ICON));
                String foodStatus = cursor.getString(cursor.getColumnIndex(ConstantKey.COLUMN_NAME_STATUS));
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
     *
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
                String unitName = cursor.getString(cursor.getColumnIndex(ConstantKey.COLUMN_NAME_UNIT_NAME));
                int unitID = cursor.getInt(cursor.getColumnIndex(ConstantKey.COLUMN_NAME_UNIT_ID));
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
     *
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
            values.put(ConstantKey.COLUMN_NAME_UNIT_NAME, newUnitName);
            long rs = db.insert(ConstantKey.TABLE_NAME_UNIT, null, values);
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
     *
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
            values.put(ConstantKey.COLUMN_NAME_UNIT_NAME, unitName);
            values.put(ConstantKey.COLUMN_NAME_UNIT_ID, unitID);
            long rs = db.update(ConstantKey.TABLE_NAME_UNIT, values, ConstantKey.COLUMN_NAME_UNIT_ID + "=" + unitID, null);
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
     *
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
                unitID = cursor.getInt(cursor.getColumnIndex(ConstantKey.COLUMN_NAME_UNIT_ID));
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
     *
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
                unit = new Unit(cursor.getString(cursor.getColumnIndex(ConstantKey.COLUMN_NAME_UNIT_NAME)), unitID);
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
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: unitID
     * @Return: boolean
     */
    public boolean checkUnit(int unitID) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM dish WHERE unitID = '" + unitID + "'", null);
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
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: unitID
     * @Return: boolean
     */
    public boolean deleteUnit(int unitID) {
        try {
            if (!checkUnit(unitID)) {
                try {
                    SQLiteDatabase db = getWritableDatabase();
                    long rs = db.delete(ConstantKey.TABLE_NAME_UNIT, ConstantKey.COLUMN_NAME_UNIT_ID + "=" + unitID, null);
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
     *
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
            String dishName = newDish.getDishName();
            long dishPrice = newDish.getDishPrice();
            int unitID = newDish.getUnitID();
            String colorBackground = newDish.getColorBackground();
            String Icon = newDish.getDishIcon();
            String Status = newDish.getDishStatus();
            values.put(ConstantKey.COLUMN_NAME_DISH_NAME, dishName);
            values.put(ConstantKey.COLUMN_NAME_DISH_PRICE, dishPrice);
            values.put(ConstantKey.COLUMN_NAME_UNIT_ID, unitID);
            values.put(ConstantKey.COLUMN_NAME_COLOR, colorBackground);
            values.put(ConstantKey.COLUMN_NAME_ICON, Icon);
            values.put(ConstantKey.COLUMN_NAME_STATUS, Status);
            long rs = db.insert(ConstantKey.TABLE_NAME_DISH, null, values);
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
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: dish
     * @Return: result
     */
    public boolean updateDish(Dish dish) {
        boolean result = false;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            int dishID = dish.getDishID();
            String dishName = dish.getDishName();
            long dishPrice = dish.getDishPrice();
            int unitID = dish.getUnitID();
            String colorBackground = dish.getColorBackground();
            String Icon = dish.getDishIcon();
            String Status = dish.getDishStatus();
            values.put(ConstantKey.COLUMN_NAME_DISH_NAME, dishName);
            values.put(ConstantKey.COLUMN_NAME_DISH_PRICE, dishPrice);
            values.put(ConstantKey.COLUMN_NAME_UNIT_ID, unitID);
            values.put(ConstantKey.COLUMN_NAME_COLOR, colorBackground);
            values.put(ConstantKey.COLUMN_NAME_ICON, Icon);
            values.put(ConstantKey.COLUMN_NAME_STATUS, Status);
            long rs = db.update(ConstantKey.TABLE_NAME_DISH, values, ConstantKey.COLUMN_NAME_DISH_ID + "=" + dishID, null);
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
