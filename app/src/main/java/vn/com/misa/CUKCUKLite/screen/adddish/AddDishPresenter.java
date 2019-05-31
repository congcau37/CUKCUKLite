package vn.com.misa.CUKCUKLite.screen.adddish;

import android.content.Context;

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
    Context mContext;
    String error = "";

    public AddDishPresenter(IAddDishModel iAddDishModel, IAddDishContract.IView iView, Context mContext) {
        this.iAddDishModel = iAddDishModel;
        this.iView = iView;
        this.mContext = mContext;
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
        try {
            if (newDish!=null) {
                boolean result;
                try {
                    result = iAddDishModel.saveNewDish(newDish);
                    if (result == true) {
                        iView.saveNewDishSuccess();
                    } else {
                        iView.saveNewDishFail(error);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
