package vn.com.misa.CUKCUKLite.screen.chooseunit;

import android.content.Context;

import vn.com.misa.CUKCUKLite.data.db.ControllerSQLite;
import vn.com.misa.CUKCUKLite.data.db.DBOpenHeplper;
import vn.com.misa.CUKCUKLite.model.Unit;

/**
 * Lớp model đơn vị
 * Create by: trand
 * Date: 5/26/2019
 */
public class ChooseUnitModel extends DBOpenHeplper implements IChooseUnitModel {
    ControllerSQLite controllerSQLite;

    public ChooseUnitModel(Context context) {
        super(context);
        controllerSQLite = new ControllerSQLite(context);
        controllerSQLite.createDataBase();
    }

    /**
     * Hàm hiển thị danh sách đơn vị
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
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
     * Hàm thêm mới đơn vị
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: newUnitName
     * @Return:
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
     * Hàm lấy ra đơn vị theo tên
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: unitName
     * @Return:
     */
    @Override
    public int getUnitID(String unitName) {
        int unitID = 0;
        try {
            unitID = controllerSQLite.getUnitID(unitName);
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

    /**
     * Hàm xóa đơn vị
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: unitID
     * @Return:
     */
    @Override
    public boolean deleteUnit(int unitID) {
        boolean check = controllerSQLite.deleteUnit(unitID);
        if (check == true){
            return true;
        }
        return false;
    }

    /**
     * Hàm cập nhật đơn vị
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
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
