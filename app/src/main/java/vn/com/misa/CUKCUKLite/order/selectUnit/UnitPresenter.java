package vn.com.misa.CUKCUKLite.order.selectUnit;

import java.util.List;

import vn.com.misa.CUKCUKLite.model.Unit;

public class UnitPresenter implements IUnitContract.IUnitPresenter {

    IUnitContract.IUnitView iUnitView;
    IUnitModel iUnitModel;

    public UnitPresenter( IUnitModel iUnitModel, IUnitContract.IUnitView iUnitView) {
        this.iUnitModel = iUnitModel;
        this.iUnitView = iUnitView;
    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    @Override
    public void loadAllUnit() {
        try {
            iUnitModel.getAllUnit(new IUnitModel.ICallbackUnit() {
                @Override
                public void getAllUnit(List<Unit> unitList) {
                    iUnitView.displayUnit(unitList);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    @Override
    public void saveNewUnit(String newUnitName) {
        boolean result;
        try {
            result = iUnitModel.saveNewUnit(newUnitName);
            if(result==true){
                int newunitID = iUnitModel.getUnitID(newUnitName);
                Unit newUnit = new Unit(newUnitName,newunitID);
                iUnitView.saveNewUnitSuccess(newUnit);
            }else {
                iUnitView.saveNewUnitFail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    @Override
    public Unit getUnit(int unitID) {
        Unit unit = null;
        try {
            unit = iUnitModel.getUnit(unitID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unit;
    }
}
