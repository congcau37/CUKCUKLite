package vn.com.misa.CUKCUKLite.screen.adddish;

import android.content.Context;

import vn.com.misa.CUKCUKLite.data.ControllerSQLite;
import vn.com.misa.CUKCUKLite.data.DBOpenHeplper;
import vn.com.misa.CUKCUKLite.model.Dish;

/**
 * Lớp model món ăn
 * @Create_by: trand
 * @Date: 5/28/2019
 * @Param:
 * @Return:
 */
public class AddDishModel extends DBOpenHeplper implements IAddDishModel {

    ControllerSQLite controllerSQLite;

    public AddDishModel(Context context) {
        super(context);
        controllerSQLite = new ControllerSQLite(context);
        controllerSQLite.createDataBase();
    }

    /**
     * Hàm thêm mới món ăn
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: Dish
     * @Return: boolean
     */
    @Override
    public boolean saveNewDish(Dish newDish) {
        boolean result = false;
        try {
            result = controllerSQLite.saveNewFood(newDish);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
