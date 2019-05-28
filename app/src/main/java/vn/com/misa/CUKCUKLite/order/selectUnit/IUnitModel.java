package vn.com.misa.CUKCUKLite.order.selectUnit;

import java.util.List;

import vn.com.misa.CUKCUKLite.model.Unit;

public interface IUnitModel {
    interface ICallbackUnit {
        void getAllUnit(List<Unit> unitList);
    }

    void getAllUnit(ICallbackUnit iCallbackUnit);

    boolean saveNewUnit(String newUnitName);

    boolean updateUnit(Unit unit);

    int getUnitID(String newUnitName);

    Unit getUnit(int unitID);

    boolean deleteUnit(int unitID);
}
