package vn.com.misa.CUKCUKLite.order.editFood;

import vn.com.misa.CUKCUKLite.model.Food;
import vn.com.misa.CUKCUKLite.model.Unit;

/**
 *
 * @Create_by: trand
 * @Date: 5/29/2019
 */
public interface IFoodContract {
    interface IFoodView{

        void saveNewFoodSuccess();

        void saveNewFoodFail(String error);

        void updateFoodSuccess();

        void updateFoodFail();
    }

    interface IFoodPresenter{

        void saveNewFood(Food newFood);

        void updateFood(Food food);
    }
}
