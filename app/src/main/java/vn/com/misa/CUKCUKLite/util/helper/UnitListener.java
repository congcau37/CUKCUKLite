package vn.com.misa.CUKCUKLite.util.helper;

import vn.com.misa.CUKCUKLite.model.Unit;

/**
 * Lớp interface đơn vị
 * @Create_by: trand
 * @Date: 5/29/2019
 */
public interface UnitListener {
    void deleteUnit(int unitID);

    void showDialogDeleteUnit(Unit unit);

    void showDialogEditUnit(Unit unit);
}
