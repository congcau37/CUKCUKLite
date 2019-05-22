package vn.com.misa.CUKCUKLite.order;

import java.util.List;

import vn.com.misa.CUKCUKLite.model.Food;

/**
 *
 * @created_by tdcong
 * @date 5/20/2019
 */
public interface IOrderContract {

    interface IOrderView {

        void displayListOrder(List<Food> arrayList);
    }

    interface IOrderPresenter {
        void loadAllFood();
    }
}
