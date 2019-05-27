package vn.com.misa.CUKCUKLite.util;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

public class AppUtil {

    /**
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    public static GradientDrawable createCircleBackground(String color) {
        GradientDrawable backgroundCircle = null;
        try {
            // tạo background
            backgroundCircle = new GradientDrawable();
            backgroundCircle.setShape(GradientDrawable.OVAL);
            backgroundCircle.setColor(Color.parseColor(color));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return backgroundCircle;
    }
}
