package vn.com.misa.CUKCUKLite.screen.adddish;

import vn.com.misa.CUKCUKLite.model.Dish;

/**
 * Lớp presenter món ăn
 * @Create_by: trand
 * @Date: 5/28/2019
 * @Param:
 * @Return:
 */
public class AddDishPresenter implements IAddDishContract.IPresenter {
    IAddDishModel iAddDishModel;
    IAddDishContract.IView iView;
    String error = "";

    public AddDishPresenter(IAddDishModel iAddDishModel, IAddDishContract.IView iView) {
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
}
