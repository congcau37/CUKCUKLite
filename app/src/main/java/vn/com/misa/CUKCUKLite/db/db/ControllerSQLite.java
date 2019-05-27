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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return unitList;
    }

    /**
     *
     * Create by: trand
     * Date: 5/26/2019
     */
    public boolean saveNewUnit(String newUnitName){
        boolean result = false;
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("unitName", newUnitName);
            long rs = db.insert("unit", null, values);
            if (rs > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

/**
 *
 * @Create_by: trand
 * @Date: 5/27/2019
 * @Param:
 * @Return:
 */
    public int getUnitID(String newUnitName){
        int unitID = 0;
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor=db.rawQuery("SELECT * FROM unit WHERE unitName= '"+newUnitName+"'",null);
            if (cursor != null) {
                cursor.moveToFirst();
                unitID = cursor.getInt(cursor.getColumnIndex("unitID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unitID;
    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    public Unit getUnit(int unitID){
        Unit unit = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor=db.rawQuery("SELECT * FROM unit WHERE unitID = '"+unitID+"'",null);
            if (cursor != null) {
                cursor.moveToFirst();
                unit = new Unit(cursor.getString(cursor.getColumnIndex("unitName")),unitID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unit;
    }
}
