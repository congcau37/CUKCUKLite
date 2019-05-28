package vn.com.misa.CUKCUKLite.order;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.CUKCUKLite.model.Food;
import vn.com.misa.CUKCUKLite.model.Unit;

/**
 * Lớp presenter thực đơn
 * @created_by tdcong
 * @date 5/10/2019
 */
public class OrderPresenter implements IOrderContract.IOrderPresenter {
    IOrderContract.IOrderView iOrderView;
    IOrder iOrder;
    List<Food> mFoodList = new ArrayList<>();

    public OrderPresenter(IOrder iOrder, IOrderContract.IOrderView iOrderView) {
        this.iOrder = iOrder;
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
            iOrder.getAllFood(new IOrder.IGetAllFoodCallBack() {
                @Override
                public void getAllFood(List<Food> foods) {
                    mFoodList = foods;
                    iOrderView.displayListOrder(foods);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
