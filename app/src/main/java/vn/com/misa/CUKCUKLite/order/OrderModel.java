package vn.com.misa.CUKCUKLite.order;

import android.content.Context;

import vn.com.misa.CUKCUKLite.db.db.ControllerSQLite;
import vn.com.misa.CUKCUKLite.db.db.DBOpenHeplper;

/**
 * Lớp model thực đơn
 * @created_by tdcong
 * @date 5/15/2019

 */
public class OrderModel extends DBOpenHeplper implements IOrder {

    ControllerSQLite controllerSQLite;

    public OrderModel(Context context) {
        super(context);
        controllerSQLite = new ControllerSQLite(context);
        controllerSQLite.createDataBase();
    }

    /**
     * Hàm lấy ra danh sách món ăn trong thực đơn
     * @param callBack
     * @return
     */
    @Override
    public void getAllFood(IGetAllFoodCallBack callBack) {
        try {
            callBack.getAllFood(controllerSQLite.getFoodFromDatabase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
