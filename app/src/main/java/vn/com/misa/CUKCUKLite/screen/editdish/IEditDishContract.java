package vn.com.misa.CUKCUKLite.screen.editdish;

import vn.com.misa.CUKCUKLite.model.Dish;

public interface IEditDishContract {
    interface IView{
        void updateDishSuccess();

        void updateDishFail();
    }
    interface IPresenter{
        void updateDish(Dish dish);
    }
}
