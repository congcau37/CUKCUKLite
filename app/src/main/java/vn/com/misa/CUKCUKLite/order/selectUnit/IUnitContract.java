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

        void saveNewUnitFail();


    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    interface IUnitPresenter {
        void loadAllUnit();

        void saveNewUnit(String newUnitName);

        Unit getUnit(int unitID);

    }
}
