package vn.com.misa.CUKCUKLite.screen.menu;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.CUKCUKLite.model.Dish;

/**
 * Lớp presenter thực đơn
 * @created_by tdcong
 * @date 5/10/2019
 */
public class MenuPresenter implements IMenuContract.IOrderPresenter {
    IMenuContract.IOrderView iOrderView;
    IMenu iMenu;
    List<Dish> mDishList = new ArrayList<>();

    public MenuPresenter(IMenu iMenu, IMenuContract.IOrderView iOrderView) {
        this.iMenu = iMenu;
        this.iOrderView = iOrderView;
    }

    /**
     * Hàm lấy ra danh sách món ăn trong thực đơn
     * @param
     * @return
     */
    @Override
    public void loadAllFood() {
        try {
            iMenu.getAllFood(new IMenu.IGetAllFoodCallBack() {
                @Override
                public void getAllFood(List<Dish> dishes) {
                    mDishList = dishes;
                    iOrderView.displayListOrder(dishes);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
