package vn.com.misa.CUKCUKLite.screen.menu;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.CUKCUKLite.model.Dish;

/**
 * Lớp presenter thực đơn
 * @created_by tdcong
 * @date 5/10/2019
 */
public class MenuPresenter implements IMenuContract.IPresenter {
    IMenuModel iMenuModel;
    IMenuContract.IView iView;
    Context mContext;
    List<Dish> mDishList = new ArrayList<>();

    public MenuPresenter(IMenuModel iMenuModel, IMenuContract.IView iView, Context mContext) {
        this.iMenuModel = iMenuModel;
        this.iView = iView;
        this.mContext = mContext;
    }

    /**
     * Hàm lấy ra danh sách món ăn trong thực đơn
     * @param
     * @return
     */
    @Override
    public void loadAllFood() {
        try {
            iMenuModel.getAllFood(new IMenuModel.IGetAllFoodCallBack() {
                @Override
                public void getAllFood(List<Dish> dishes) {
                    mDishList = dishes;
                    iView.displayListOrder(dishes);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
