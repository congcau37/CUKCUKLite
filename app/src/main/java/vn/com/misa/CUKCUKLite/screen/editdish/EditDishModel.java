package vn.com.misa.CUKCUKLite.screen.editdish;

import android.content.Context;

import vn.com.misa.CUKCUKLite.data.ControllerSQLite;
import vn.com.misa.CUKCUKLite.data.DBOpenHeplper;
import vn.com.misa.CUKCUKLite.model.Dish;

public class EditDishModel extends DBOpenHeplper implements IEditDishModel {

    ControllerSQLite controllerSQLite;
    public EditDishModel(Context context) {
        super(context);
        controllerSQLite = new ControllerSQLite(context);
        controllerSQLite.createDataBase();
    }

    /**
     * Hàm cập nhật món ăn
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: Dish
     * @Return: boolean
     */
    @Override
    public boolean updateDish(Dish dish) {
        boolean result = false;
        try {
            result = controllerSQLite.updateDish(dish);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Mục đích Methob:
     * @created_by tdcong
     * @date 6/3/2019
     * @param: dish:món ăn
     * @return: boolean
     */
    @Override
    public boolean deleteDish(Dish dish) {
        boolean result = false;
        try {
            result = controllerSQLite.deleteDish(dish);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
