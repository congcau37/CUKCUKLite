package vn.com.misa.CUKCUKLite.screen.caculator.model;

/**
 * Lớp để người dùng
 * @Create_by: trand
 * @Date: 6/4/2019
 */

public class Operators {
    private int id;
    private long value;

    public Operators(int id, long value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Operators setId(int id) {
        this.id = id;
        return this;
    }

    public long getValue() {
        return value;
    }

    public Operators setValue(long value) {
        this.value = value;
        return this;
    }
}
