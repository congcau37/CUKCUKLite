package vn.com.misa.CUKCUKLite.util.helper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
     *
     * @Create_by: trand
     * @Date: 6/4/2019
     * @Param:
     * @Return:
     */
    public static String covertColorToHexString(int color){
        return String.format("#%06X", 0xFFFFFF & color);
    }

    /**
     * chuyển định dạng tiền đã nhập sang kiểu long
     * @Create_by: trand
     * @Date: 6/4/2019
     */
    public static long getNumberInput(String text) {
        try {
            if (text.isEmpty()) {
                return 0;
            }
            text = text.replaceAll("\\.", "");
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @Create_by: trand
     * @Date: 6/4/2019
     * @Param:
     * @Return:
     */
    public static String formatAmount(long num) {
        DecimalFormat decimalFormat = new DecimalFormat();
        DecimalFormatSymbols decimalFormateSymbol = new DecimalFormatSymbols();
        decimalFormateSymbol.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormateSymbol);
        return decimalFormat.format(num);
    }

}
