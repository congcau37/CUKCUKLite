package vn.com.misa.CUKCUKLite.order.selectUnit;

import java.util.List;

import vn.com.misa.CUKCUKLite.model.Unit;

public interface IUnitContract {
    interface IUnitView{
        void displayUnit(List<Unit> unitList);
    }
    interface IUnitPresenter{
        void loadUnit();
    }
}
