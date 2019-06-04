package vn.com.misa.CUKCUKLite.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.IOException;
import java.io.InputStream;

import vn.com.misa.CUKCUKLite.R;

/**
 *
 * @Create_by: trand
 * @Date: 5/28/2019
 */
public class AppUtil {

    /**
     * Hàm bo tròn ảnh
     *
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
     *
     * @created_by tdcong
     * @date 6/3/2019
     * @param:
     * @return:
     */
    public static Drawable setCircleBackground(int color, Context context) {
        Drawable drawableBg = context.getResources().getDrawable(R.drawable.bg_circle);
        drawableBg.setColorFilter(color, PorterDuff.Mode.SRC);
        return drawableBg;
    }

    /**
     * hàm đọc file json từ asset folder
     * @Create_by: trand
     * @Date: 6/4/2019
     * @Param: context, file name: tên file
     * @Return: String chuỗi
     */
    public static String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            InputStream inputStream = context.getAssets().open(fileName);

            int size = inputStream.available();

            byte[] buffer = new byte[size];

            inputStream.read(buffer);

            inputStream.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
