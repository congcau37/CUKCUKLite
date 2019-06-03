package vn.com.misa.CUKCUKLite.screen.editdish;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

import vn.com.misa.CUKCUKLite.model.Dish;
import vn.com.misa.CUKCUKLite.util.ConstantKey;

/**
 * Lớp: 
 * tdcong
 * 5/30/2019
 */
public class EditDishPresenter implements IEditDishContract.IPresenter{

    IEditDishModel iAddDishModel;
    IEditDishContract.IView iView;
    Context mContext;
    String error = "";

    public EditDishPresenter(IEditDishModel iAddDishModel, IEditDishContract.IView iView, Context mContext) {
        this.iAddDishModel = iAddDishModel;
        this.iView = iView;
        this.mContext = mContext;
    }

    /**
     * Hàm cập nhật món ăn
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: Dish
     * @Return:
     */
    @Override
    public void updateDish(Dish dish) {
        if (dish!=null) {
            boolean result;
            try {
                result = iAddDishModel.updateDish(dish);
                if (result == true) {
                    iView.updateDishSuccess();
                } else {
                    iView.updateDishFail();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Mục đích Methob: Xóa món
     * @created_by tdcong
     * @date 6/3/2019
     * @param: dish món
     */
    @Override
    public void deleteDish(Dish dish) {
        if (dish!=null) {
            boolean result;
            try {
                result = iAddDishModel.deleteDish(dish);
                if (result == true) {
                    iView.deleteDishSuccess();
                } else {
                    iView.deleteDishFail();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Mục đích Methob: lấy ảnh từ asset
     * @created_by tdcong
     * @date 6/3/2019
     * @param: context, icon
     * @return: bitmap
     */
    @Override
    public Bitmap getBitmapFromAssets(Context context, String icon) {
        AssetManager assetManager = context.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(ConstantKey.PACKAGE_ICON + icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(istr);
    }
}
