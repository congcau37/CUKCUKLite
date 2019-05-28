package vn.com.misa.CUKCUKLite.model;

import java.io.Serializable;

/**
 * Lớp đơn vị tính
 * @Create_by: trand
 * @Date: 5/28/2019
 */
public class Unit implements Serializable {
    String unitName;
    int unitID;

    public Unit(String unitName, int unitID) {
        this.unitName = unitName;
        this.unitID = unitID;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getUnitID() {
        return unitID;
    }

    public void setUnitID(int unitID) {
        this.unitID = unitID;
    }
}
