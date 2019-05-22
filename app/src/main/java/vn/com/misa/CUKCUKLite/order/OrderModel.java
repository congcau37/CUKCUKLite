package vn.com.misa.CUKCUKLite.order;

import android.content.Context;

import vn.com.misa.CUKCUKLite.db.SQLite.ControllerSQLite;
import vn.com.misa.CUKCUKLite.db.SQLite.DBOpenHeplper;

/**
 *
 * @created_by tdcong
 * @date 5/15/2019
 * @param
 * @return
 */
public class OrderModel extends DBOpenHeplper implements IOrder {

    ControllerSQLite controllerSQLite;

    public OrderModel(Context context) {
        super(context);
        controllerSQLite = new ControllerSQLite(context);
        controllerSQLite.createDataBase();
    }

/**
 * Hàm
 * @param
 * @return
 */
    @Override
    public void getAllFood(IGetAllFoodCallBack callBack) {
        try {
            callBack.getAllFood(controllerSQLite.getFoodArrayList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
