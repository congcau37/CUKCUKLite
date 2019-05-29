package vn.com.misa.CUKCUKLite.screen.adddish;

import vn.com.misa.CUKCUKLite.model.Dish;

/**
 *
 * @Create_by: trand
 * @Date: 5/29/2019
 */
public interface AddDishContract {
    interface IView {

        void saveNewDishSuccess();

        void saveNewDishFail(String error);

        void updateDishSuccess();

        void updateDishFail();
    }

    interface IPresenter {

        void saveNewDish(Dish newDish);

        void updateDish(Dish dish);
    }
}
