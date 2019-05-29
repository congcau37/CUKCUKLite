package vn.com.misa.CUKCUKLite.screen.menu;

import java.util.List;

import vn.com.misa.CUKCUKLite.model.Dish;

/**
 *
 * @created_by tdcong
 * @date 5/20/2019
 */
public interface IMenuContract {

    interface IOrderView {

        void displayListOrder(List<Dish> arrayList);


    }
    interface IOrderPresenter {

        void loadAllFood();

    }

}
