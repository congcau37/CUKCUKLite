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
 * @created_by tdcong
 * @date 5/20/2019
 */
public class ControllerSQLite extends DBOpenHeplper {

    public ControllerSQLite(Context context) {

        super(context);
    }

    /**
     * hàm lấy danh sách món ăn từ database
     *
     * @param
     * @return
     * @created_by tdcong
     * @date 5/23/2019
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
     * Create by: trand
     * Date: 5/26/2019
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
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
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
            long rs = db.update("unit", values, "unitID" + "=" + unitID,null);
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
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
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
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
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
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    public boolean checkUnit(int unitID) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM food WHERE unitID = '" + unitID + "'", null);
            if (cursor.getCount() >0) {
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
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    public boolean deleteUnit(int unitID) {
        try {
            if (checkUnit(unitID) == false) {
                try {
                    SQLiteDatabase db = getWritableDatabase();
                    long rs = db.delete("unit", "unitID="+unitID, null);
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
}
