package vn.com.misa.CUKCUKLite.order.addFood.selectUnit;

import android.content.Context;

import vn.com.misa.CUKCUKLite.db.db.ControllerSQLite;
import vn.com.misa.CUKCUKLite.db.db.DBOpenHeplper;

public class UnitModel extends DBOpenHeplper implements IUnitModel{

    ControllerSQLite controllerSQLite;

    public UnitModel(Context context) {
        super(context);
        controllerSQLite = new ControllerSQLite(context);
        controllerSQLite.createDataBase();
    }

    @Override
    public void getAllUnit(ICallbackUnit iCallbackUnit) {
        try {
            iCallbackUnit.getAllUnit(controllerSQLite.getUnitFromDatabase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveNewUnit(String newUnit) {
        controllerSQLite.saveNewUnit(newUnit);
    }
}
