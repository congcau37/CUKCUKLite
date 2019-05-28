package vn.com.misa.CUKCUKLite.order.selectUnit;

import android.content.Context;

import vn.com.misa.CUKCUKLite.db.db.ControllerSQLite;
import vn.com.misa.CUKCUKLite.db.db.DBOpenHeplper;
import vn.com.misa.CUKCUKLite.model.Unit;

/**
 * Create by: trand
 * Date: 5/26/2019
 */
public class UnitModel extends DBOpenHeplper implements IUnitModel {

    ControllerSQLite controllerSQLite;

    /**
     * Create by: trand
     * Date: 5/26/2019
     */
    public UnitModel(Context context) {
        super(context);
        controllerSQLite = new ControllerSQLite(context);
        controllerSQLite.createDataBase();
    }

    /**
     * Create by: trand
     * Date: 5/26/2019
     */
    @Override
    public void getAllUnit(ICallbackUnit iCallbackUnit) {
        try {
            iCallbackUnit.getAllUnit(controllerSQLite.getUnitFromDatabase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create by: trand
     * Date: 5/26/2019
     */
    @Override
    public boolean saveNewUnit(String newUnitName) {
        boolean result = false;
        try {
            result = controllerSQLite.saveNewUnit(newUnitName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    @Override
    public int getUnitID(String newUnitName) {
        int unitID = 0;
        try {
            unitID = controllerSQLite.getUnitID(newUnitName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unitID;
    }

    /**
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    @Override
    public Unit getUnit(int unitID) {
        Unit unit = null;
        try {
            unit = controllerSQLite.getUnit(unitID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unit;
    }

    @Override
    public boolean deleteUnit(int unitID) {
        boolean check = controllerSQLite.deleteUnit(unitID);
        if (check == true){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUnit(Unit unit) {
        boolean result = false;
        try {
            result = controllerSQLite.updateUnit(unit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
