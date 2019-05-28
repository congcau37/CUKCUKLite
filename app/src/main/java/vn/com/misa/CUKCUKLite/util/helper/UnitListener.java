package vn.com.misa.CUKCUKLite.util.helper;

import vn.com.misa.CUKCUKLite.model.Unit;

public interface UnitListener {
    void deleteUnit(int unitID);

    void showDialogDeleteUnit(Unit unit);

    void showDialogEditUnit(Unit unit);
}
