package vn.com.misa.CUKCUKLite.order.editFood;

import android.content.Context;

import vn.com.misa.CUKCUKLite.db.db.ControllerSQLite;
import vn.com.misa.CUKCUKLite.db.db.DBOpenHeplper;
import vn.com.misa.CUKCUKLite.model.Food;

/**
 * Lớp model món ăn
 * @Create_by: trand
 * @Date: 5/28/2019
 * @Param:
 * @Return:
 */
public class FoodModel extends DBOpenHeplper implements IFoodModel{

    ControllerSQLite controllerSQLite;

    public FoodModel(Context context) {
        super(context);
        controllerSQLite = new ControllerSQLite(context);
        controllerSQLite.createDataBase();
    }

    /**
     * Hàm thêm mới món ăn
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: Food
     * @Return: boolean
     */
    @Override
    public boolean saveNewFood(Food newFood) {
        boolean result = false;
        try {
            result = controllerSQLite.saveNewFood(newFood);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Hàm cập nhật món ăn
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: Food
     * @Return: boolean
     */
    @Override
    public boolean updateFood(Food food) {
        boolean result = false;
        try {
            result = controllerSQLite.updateFood(food);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
