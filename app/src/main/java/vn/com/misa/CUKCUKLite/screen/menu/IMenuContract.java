package vn.com.misa.CUKCUKLite.screen.menu;

import java.util.List;

import vn.com.misa.CUKCUKLite.model.Dish;

/**
 *
 * @created_by tdcong
 * @date 5/20/2019
 */
public interface IMenuContract {

    interface IView {

        void displayListOrder(List<Dish> arrayList);


    }
    interface IPresenter {

        void loadAllFood();

    }

}
