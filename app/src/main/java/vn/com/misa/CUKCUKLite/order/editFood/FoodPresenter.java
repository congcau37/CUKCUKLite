package vn.com.misa.CUKCUKLite.order.editFood;

import vn.com.misa.CUKCUKLite.model.Food;
import vn.com.misa.CUKCUKLite.model.Unit;

/**
 * Lớp presenter món ăn
 * @Create_by: trand
 * @Date: 5/28/2019
 * @Param:
 * @Return:
 */
public class FoodPresenter implements IFoodContract.IFoodPresenter {
    IFoodModel iFoodModel;
    IFoodContract.IFoodView iFoodView;
    String error = "";

    public FoodPresenter(IFoodModel iFoodModel, IFoodContract.IFoodView iFoodView) {
        this.iFoodModel = iFoodModel;
        this.iFoodView = iFoodView;
    }

    /**
     * Hàm thêm mới món ăn
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    @Override
    public void saveNewFood(Food newFood) {
        boolean result;
        try {
            result = iFoodModel.saveNewFood(newFood);
            if (result == true) {
                iFoodView.saveNewFoodSuccess();
            } else {
                error = "";
                iFoodView.saveNewFoodFail(error);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm cập nhật món ăn
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: Food
     * @Return:
     */
    @Override
    public void updateFood(Food food) {
        boolean result;
        try {
            result = iFoodModel.updateFood(food);
            if (result == true) {
                iFoodView.updateFoodSuccess();
            } else {
                iFoodView.updateFoodFail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
