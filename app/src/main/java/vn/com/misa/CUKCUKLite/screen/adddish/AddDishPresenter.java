package vn.com.misa.CUKCUKLite.screen.adddish;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

import vn.com.misa.CUKCUKLite.model.Dish;
import vn.com.misa.CUKCUKLite.util.ConstantKey;

/**
 * Lớp presenter món ăn
 * @Create_by: trand
 * @Date: 5/28/2019
 * @Param:
 * @Return:
 */
public class AddDishPresenter implements IAddDishContract.IPresenter {
    IAddDishModel iAddDishModel;
    IAddDishContract.IView iView;
    Context mContext;
    String error = "";

    public AddDishPresenter(IAddDishModel iAddDishModel, IAddDishContract.IView iView, Context mContext) {
        this.iAddDishModel = iAddDishModel;
        this.iView = iView;
        this.mContext = mContext;
    }

    /**
     * Hàm thêm mới món ăn
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    @Override
    public void saveNewDish(Dish newDish) {
        try {
            if (newDish!=null) {
                boolean result;
                try {
                    result = iAddDishModel.saveNewDish(newDish);
                    if (result == true) {
                        iView.saveNewDishSuccess();
                    } else {
                        iView.saveNewDishFail(error);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích Methob:
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
