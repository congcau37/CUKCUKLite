package vn.com.misa.CUKCUKLite.screen.editdish;

import android.content.Context;

import vn.com.misa.CUKCUKLite.model.Dish;

/**
 * Lớp: 
 * tdcong
 * 5/30/2019
 */
public class EditDishPresenter implements IEditDishContract.IPresenter{

    IEditDishModel iAddDishModel;
    IEditDishContract.IView iView;
    Context mContext;
    String error = "";

    public EditDishPresenter(IEditDishModel iAddDishModel, IEditDishContract.IView iView, Context mContext) {
        this.iAddDishModel = iAddDishModel;
        this.iView = iView;
        this.mContext = mContext;
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
