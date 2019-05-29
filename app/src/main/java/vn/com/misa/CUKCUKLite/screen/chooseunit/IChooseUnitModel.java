package vn.com.misa.CUKCUKLite.screen.chooseunit;

import java.util.List;

import vn.com.misa.CUKCUKLite.model.Unit;

/**
 * Lớp model đơn vị
 * @Create_by: trand
 * @Date: 5/29/2019
 */
public interface IChooseUnitModel {
    interface ICallbackUnit {
        void getAllUnit(List<Unit> unitList);
    }

    void getAllUnit(ICallbackUnit iCallbackUnit);

    boolean saveNewUnit(String newUnitName);

    boolean updateUnit(Unit unit);

    int getUnitID(String unitName);

    Unit getUnit(int unitID);

    boolean deleteUnit(int unitID);
}
