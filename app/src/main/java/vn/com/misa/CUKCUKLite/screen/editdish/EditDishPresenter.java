package vn.com.misa.CUKCUKLite.screen.editdish;

import vn.com.misa.CUKCUKLite.model.Dish;

public class EditDishPresenter implements IEditDishContract.IPresenter{

    IEditDishModel iAddDishModel;
    IEditDishContract.IView iView;
    String error = "";

    public EditDishPresenter(IEditDishModel iEditDishModel, IEditDishContract.IView iView) {
        this.iAddDishModel = iEditDishModel;
        this.iView = iView;
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
