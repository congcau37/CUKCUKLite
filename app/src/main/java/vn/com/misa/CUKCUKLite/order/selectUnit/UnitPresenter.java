package vn.com.misa.CUKCUKLite.order.selectUnit;

import java.util.List;

import vn.com.misa.CUKCUKLite.model.Unit;

/**
 * Lớp presenter đơn vị
 * @Create_by: trand
 * @Date: 5/29/2019
 * @Param:
 * @Return:
 */
public class UnitPresenter implements IUnitContract.IUnitPresenter {

    IUnitContract.IUnitView iUnitView;
    IUnitModel iUnitModel;
    boolean checkUnit;
    String error = "";

    public UnitPresenter( IUnitModel iUnitModel, IUnitContract.IUnitView iUnitView) {
        this.iUnitModel = iUnitModel;
        this.iUnitView = iUnitView;
    }

    /**
     * Hàm hiển thị danh sách đơn vị
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
     * Hàm thêm mới đơn vị
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: newUnitName
     * @Return:
     */
    @Override
    public void saveNewUnit(String newUnitName) {
        boolean result;
        try {
            if (checkUnitData(newUnitName) == false){
                result = iUnitModel.saveNewUnit(newUnitName);
                if(result==true){
                    int newunitID = iUnitModel.getUnitID(newUnitName);
                    Unit newUnit = new Unit(newUnitName,newunitID);
                    iUnitView.saveNewUnitSuccess(newUnit);
                }else {
                    iUnitView.saveNewUnitFail(error);
                }
            }else {
                error = "Đơn vị <"+newUnitName+"> đã tồn tại";
                iUnitView.saveNewUnitFail(error);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm kiểm tra đơn vị đã tồn tại chưa
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: unitName
     * @Return: boolean
     */
    @Override
    public boolean checkUnitData(final String unitName) {
        iUnitModel.getAllUnit(new IUnitModel.ICallbackUnit() {
            @Override
            public void getAllUnit(List<Unit> unitList) {
                for (int i = 0;i<unitList.size();i++){
                    if(unitName.equals(unitList.get(i).getUnitName())){
                        checkUnit = true;
                    }
                }
            }
        });
        return checkUnit;
    }

    /**
     * Hàm cập nhật đơn vị
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @Override
    public void updateUnit(Unit unit) {
        boolean result;
        try {
            result = iUnitModel.updateUnit(unit);
            if(result==true){
                iUnitView.updateUnitSuccess();
            }else {
                iUnitView.updateUnitFail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * hàm lấy đơn vị theo ID
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: unitID
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

    /**
     * Hàm xóa đơn vị
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: unitID
     * @Return: 
     */
    @Override
    public void deleteUnit(int unitID) {
        boolean check = iUnitModel.deleteUnit(unitID);
        try {
            if(check==true){
                iUnitView.deleteUnitSuccess();
            }else {
                iUnitView.deleteUnitFail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
