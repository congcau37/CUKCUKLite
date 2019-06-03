package vn.com.misa.CUKCUKLite.screen.adddish;

import android.content.Context;
import android.graphics.Bitmap;

import vn.com.misa.CUKCUKLite.model.Dish;

/**
 *
 * @Create_by: trand
 * @Date: 5/29/2019
 */
public interface IAddDishContract {
    interface IView {

        void saveNewDishSuccess();

        void saveNewDishFail(String error);
    }

    interface IPresenter {

        void saveNewDish(Dish newDish);

        Bitmap getBitmapFromAssets(Context context, String icon);

    }
}
