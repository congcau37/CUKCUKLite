package vn.com.misa.CUKCUKLite.order.selectUnit;

import java.util.List;

import vn.com.misa.CUKCUKLite.model.Unit;

/**
 *
 * @Create_by: trand
 * @Date: 5/27/2019
 * @Param:
 * @Return:
 */
public interface IUnitContract {
    interface IUnitView {
        void displayUnit(List<Unit> unitList);

        void saveNewUnitSuccess(Unit newUnit);

        void saveNewUnitFail(String error);

        void updateUnitSuccess();

        void updateUnitFail();

        void deleteUnitSuccess();

        void deleteUnitFail();

    }

    interface IUnitPresenter {
        void loadAllUnit();

        void saveNewUnit(String newUnitName);

        void updateUnit(Unit unit);

        Unit getUnit(int unitID);

        void deleteUnit(int unitID);

        boolean checkUnitData(String unitName);

    }
}
