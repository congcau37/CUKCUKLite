package vn.com.misa.CUKCUKLite.util.helper;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Lớp chuyển đổi định dạng
 * @Create_by: trand
 * @Date: 5/27/2019
 */
public class Converter {
    /**
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    public static boolean convertStatusMenu(String dStatus) {
        boolean status;
        if (dStatus.equals("false")) {
            status = false;
        } else {
            status = true;
        }
        return status;
    }

    /**
     * Mục đích Methob:
     * @created_by tdcong
     * @date 6/3/2019
     * @param: number số dạng chuỗi
     * @return: long tiền
     */
    public static long convertToLong(String number) {
        String currency="";
        String [] splitNumber = number.split("\\.");
        for (String newNumber: splitNumber) {
        currency += newNumber;
        }
        return Long.parseLong(String.valueOf(currency));
    }

    public static String covertColorToHexString(int color){
        return String.format("#%06X", 0xFFFFFF & color);
    }
}
