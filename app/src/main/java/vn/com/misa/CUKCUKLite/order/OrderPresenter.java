package vn.com.misa.CUKCUKLite.order;

import java.util.List;

import vn.com.misa.CUKCUKLite.model.Food;

/**
 * @param
 * @return
 * @created_by tdcong
 * @date 5/10/2019
 */
public class OrderPresenter implements IOrderContract.IOrderPresenter {
    IOrderContract.IOrderView iOrderView;
    IOrder iOrder;

    public OrderPresenter(IOrder iOrder, IOrderContract.IOrderView iOrderView) {
        this.iOrder = iOrder;
        this.iOrderView = iOrderView;
    }

    /**
     * Hàm
     * @param
     * @return
     */
    @Override
    public void loadAllFood() {
        try {
            iOrder.getAllFood(new IOrder.IGetAllFoodCallBack() {
                @Override
                public void getAllFood(List<Food> foods) {
                    iOrderView.displayListOrder(foods);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
