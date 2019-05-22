package vn.com.misa.CUKCUKLite.db.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import vn.com.misa.CUKCUKLite.db.DataManager;
import vn.com.misa.CUKCUKLite.model.Food;

import static android.support.constraint.Constraints.TAG;

/**
 *
 * @created_by tdcong
 * @date 5/20/2019
 */
public class ControllerSQLite extends DBOpenHeplper implements DataManager.SQLite {

    public ControllerSQLite(Context context) {
        super(context);
    }

    @Override
        public ArrayList<Food> getFoodArrayList() {
        ArrayList<Food> food = new ArrayList<>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * from food", null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int foodID = cursor.getInt(cursor.getColumnIndex("foodID"));
                String foodName = cursor.getString(cursor.getColumnIndex("foodName"));
                int foodPrice = cursor.getInt(cursor.getColumnIndex("foodPrice"));
                String unitID = cursor.getString(cursor.getColumnIndex("unitID"));
                String colorBackground = cursor.getString(cursor.getColumnIndex("colorBackground"));
                String foodIcon = cursor.getString(cursor.getColumnIndex("foodIcon"));
                String foodStatus = cursor.getString(cursor.getColumnIndex("foodStatus"));
                food.add(new Food(foodID, foodName, foodPrice, unitID, colorBackground, foodIcon, foodStatus));
                cursor.moveToNext();
            }

            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return food;
    }
}
