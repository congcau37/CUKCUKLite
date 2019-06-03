package vn.com.misa.CUKCUKLite.screen.editdish;

import android.content.Context;
import android.graphics.Bitmap;

import vn.com.misa.CUKCUKLite.model.Dish;

public interface IEditDishContract {
    interface IView{
        void updateDishSuccess();

        void updateDishFail();

        void deleteDishSuccess();

        void deleteDishFail();


    }
    interface IPresenter{
        void updateDish(Dish dish);

        void deleteDish(Dish dish);

        Bitmap getBitmapFromAssets(Context context, String icon);
    }
}
