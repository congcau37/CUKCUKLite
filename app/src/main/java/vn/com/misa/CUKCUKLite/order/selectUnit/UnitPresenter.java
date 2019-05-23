package vn.com.misa.CUKCUKLite.order.selectUnit;

import java.util.List;

import vn.com.misa.CUKCUKLite.model.Unit;

public class UnitPresenter implements IUnitContract.IUnitPresenter {

    IUnitContract.IUnitView iUnitView;
    IUnitModel iUnitModel;

    public UnitPresenter(IUnitContract.IUnitView iUnitView, IUnitModel iUnitModel) {
        this.iUnitView = iUnitView;
        this.iUnitModel = iUnitModel;
    }

    @Override
    public void loadUnit() {
        iUnitModel.getAllUnit(new IUnitModel.ICallbackUnit() {
            @Override
            public void getAllUnit(List<Unit> unitList) {
                iUnitView.displayUnit(unitList);
            }
        });
    }
}
