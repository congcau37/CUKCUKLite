package vn.com.misa.CUKCUKLite.order.addFood.selectUnit;

import java.util.List;

import vn.com.misa.CUKCUKLite.model.Unit;

public interface IUnitContract {
    interface IUnitView{
        void displayUnit(List<Unit> unitList);

        void saveNewUnitSuccess();

        void saveNewUnitFail();

    }
    interface IUnitPresenter{
        void loadUnit();

        void saveNewUnit(String newUnit);
    }
}
