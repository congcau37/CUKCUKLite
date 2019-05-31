package vn.com.misa.CUKCUKLite.screen.chooseunit;

import java.util.List;

import vn.com.misa.CUKCUKLite.model.Unit;

/**
 *
 * @Create_by: trand
 * @Date: 5/27/2019
 * @Param:
 * @Return:
 */
public interface IChooseUnitContract {
    interface IView {
        void displayUnit(List<Unit> unitList);

        void saveNewUnitSuccess(Unit newUnit);

        void saveNewUnitFail(String error);

        void updateUnitSuccess(Unit unit);

        void updateUnitFail(String error);

        void deleteUnitSuccess(int id);

        void deleteUnitFail();

    }

    interface IPresenter {
        void loadAllUnit();

        void saveNewUnit(String newUnitName);

        void updateUnit(Unit unit);

        Unit getUnit(int unitID);

        void deleteUnit(int unitID);

        boolean checkUnitData(String unitName);

    }
}
