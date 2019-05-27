package vn.com.misa.CUKCUKLite.util.helper;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @Create_by: trand
 * @Date: 5/27/2019
 * @Param:
 * @Return:
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

    public static String convertToCurrency(float number) {
        NumberFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(number);
    }

    public static String convertToCurrency(long number) {
        NumberFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(number);
    }

}
