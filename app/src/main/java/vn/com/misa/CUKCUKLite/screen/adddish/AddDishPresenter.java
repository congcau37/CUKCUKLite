package vn.com.misa.CUKCUKLite.screen.adddish;

import vn.com.misa.CUKCUKLite.model.Dish;

/**
 * Lớp presenter món ăn
 * @Create_by: trand
 * @Date: 5/28/2019
 * @Param:
 * @Return:
 */
public class AddDishPresenter implements AddDishContract.IPresenter {
    IAddDishModel iAddDishModel;
    AddDishContract.IView iView;
    String error = "";

    public AddDishPresenter(IAddDishModel iAddDishModel, AddDishContract.IView iView) {
        this.iAddDishModel = iAddDishModel;
        this.iView = iView;
    }

    /**
     * Hàm thêm mới món ăn
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    @Override
    public void saveNewDish(Dish newDish) {
        boolean result;
        try {
            result = iAddDishModel.saveNewDish(newDish);
            if (result == true) {
                iView.saveNewDishSuccess();
            } else {
                error = "";
                iView.saveNewDishFail(error);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm cập nhật món ăn
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: Dish
     * @Return:
     */
    @Override
    public void updateDish(Dish dish) {
        boolean result;
        try {
            result = iAddDishModel.updateDish(dish);
            if (result == true) {
                iView.updateDishSuccess();
            } else {
                iView.updateDishFail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
