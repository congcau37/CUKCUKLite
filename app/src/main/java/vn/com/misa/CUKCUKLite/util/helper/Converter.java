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
    public static boolean convertStatusOrder(String num) {
        boolean status;
        if (num.equals("1")) {
            status = false;
        } else {
            status = true;
        }
        return status;
    }

    public static long convertToLong(String number) {
        String currency="";
        String [] splitNumber = number.split(",");
        for (String newNumber: splitNumber) {
        currency += newNumber;
        }
        return Long.parseLong(String.valueOf(currency));
    }

    public static String convertToCurrency(long number) {
        NumberFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(number);
    }

}
