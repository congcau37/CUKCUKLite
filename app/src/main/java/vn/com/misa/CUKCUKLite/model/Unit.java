package vn.com.misa.CUKCUKLite.model;

import java.io.Serializable;

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
