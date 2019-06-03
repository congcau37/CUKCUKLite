package vn.com.misa.CUKCUKLite.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import vn.com.misa.CUKCUKLite.R;

/**
 *
 * @Create_by: trand
 * @Date: 5/28/2019
 */
public class AppUtil {

    /**
     * Hàm bo tròn ảnh
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: String:color
     * @Return: GradientDrawable
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

    /**
     * Mục đích Methob:
     * @created_by tdcong
     * @date 6/3/2019
     * @param:
     * @return:
     */
    public static Drawable setCircleBackground(int color, Context context){
        Drawable drawableBg = context.getResources().getDrawable(R.drawable.bg_circle);
        drawableBg.setColorFilter(color, PorterDuff.Mode.SRC);
        return drawableBg;
    }
}
