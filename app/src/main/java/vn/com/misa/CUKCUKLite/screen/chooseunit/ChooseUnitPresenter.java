package vn.com.misa.CUKCUKLite.screen.chooseunit;

import android.content.Context;

import java.util.List;

import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.model.Unit;

/**
 * Lớp presenter đơn vị
 *
 * @Create_by: trand
 * @Date: 5/29/2019
 * @Param:
 * @Return:
 */
public class ChooseUnitPresenter implements IChooseUnitContract.IPresenter {

    IChooseUnitContract.IView iView;
    IChooseUnitModel iChooseUnitModel;
    Context mContext;
    boolean checkUnit;
    String error = "";

    public ChooseUnitPresenter(IChooseUnitModel iChooseUnitModel, IChooseUnitContract.IView iView, Context mContext) {
        this.iChooseUnitModel = iChooseUnitModel;
        this.iView = iView;
        this.mContext = mContext;
    }

    /**
     * Hàm hiển thị danh sách đơn vị
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    @Override
    public void loadAllUnit() {
        try {
            iChooseUnitModel.getAllUnit(new IChooseUnitModel.ICallbackUnit() {
                @Override
                public void getAllUnit(List<Unit> unitList) {
                    iView.displayUnit(unitList);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm thêm mới đơn vị
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: newUnitName
     * @Return:
     */
    @Override
    public void saveNewUnit(String newUnitName) {
        boolean result;
        try {
            if (checkUnitData(newUnitName) == false) {
                result = iChooseUnitModel.saveNewUnit(newUnitName);
                if (result == true) {
                    int newunitID = iChooseUnitModel.getUnitID(newUnitName);
                    Unit newUnit = new Unit(newUnitName, newunitID);
                    iView.saveNewUnitSuccess(newUnit);
                } else {
                    iView.saveNewUnitFail(error);
                }
            } else {
                error = String.format(mContext.getString(R.string.not_exist_unit),newUnitName);
                iView.saveNewUnitFail(error);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm cập nhật đơn vị
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @Override
    public void updateUnit(Unit unit) {
        try {
            if (unit!=null) {
                boolean result;
                try {
                    if (unit.getUnitName().equals(getUnit(unit.getUnitID()).getUnitName())) {
                        iView.updateUnitSuccess(unit);
                    } else if (checkUnitData(unit.getUnitName()) == false) {
                        result = iChooseUnitModel.updateUnit(unit);
                        if (result == true) {
                            iView.updateUnitSuccess(unit);
                        } else {
                            iView.updateUnitFail(error);
                        }
                    } else {
                        error = String.format(mContext.getString(R.string.not_exist_unit),unit.getUnitName());
                        iView.updateUnitFail(error);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * hàm lấy đơn vị theo ID
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: unitID
     * @Return:
     */
    @Override
    public Unit getUnit(int unitID) {
        Unit unit = null;
        try {
            unit = iChooseUnitModel.getUnit(unitID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unit;
    }

    /**
     * Hàm kiểm tra đơn vị đã tồn tại chưa
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: unitName
     * @Return: boolean
     */
    @Override
    public boolean checkUnitData(final String unitName) {
        checkUnit = false;
        iChooseUnitModel.getAllUnit(new IChooseUnitModel.ICallbackUnit() {
            @Override
            public void getAllUnit(List<Unit> unitList) {
                for (int i = 0; i < unitList.size(); i++) {
                    if (unitName.equals(unitList.get(i).getUnitName())) {
                        checkUnit = true;
                    }
                }
            }
        });
        return checkUnit;
    }

    /**
     * Hàm xóa đơn vị
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: unitID
     * @Return:
     */
    @Override
    public void deleteUnit(int unitID) {
            boolean check = iChooseUnitModel.deleteUnit(unitID);
            try {
                if (check == true) {
                    iView.deleteUnitSuccess(unitID);
                } else {
                    iView.deleteUnitFail();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

}
