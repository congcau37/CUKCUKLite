package vn.com.misa.CUKCUKLite.order.addFood.selectUnit;

import java.util.List;

import vn.com.misa.CUKCUKLite.model.Unit;

public interface IUnitModel {
    interface ICallbackUnit {
        void getAllUnit(List<Unit> unitList);
    }

    void getAllUnit(ICallbackUnit iCallbackUnit);

    void saveNewUnit(String newUnit);
}
