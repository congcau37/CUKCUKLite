package vn.com.misa.CUKCUKLite.order.AddFood;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import vn.com.misa.CUKCUKLite.R;

/**
 *
 * @created_by tdcong
 * @date 5/20/2019
 */
public class CustomAddFood extends LinearLayout {

    TextView tvTitle, tvValue;
    ImageView ivNext;

    public CustomAddFood(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CustomAddFood(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomAddFood(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Options);
        String title = typedArray.getString(R.styleable.Options_tvText);
        String value = typedArray.getString(R.styleable.Options_tvValue);
        int image = typedArray.getResourceId(R.styleable.Options_ivNext,0);

        typedArray.recycle();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_row_add_food, this, true);

        tvTitle = findViewById(R.id.tvTitle);
        tvValue = findViewById(R.id.tvValue);
        ivNext = findViewById(R.id.ivNext);

        tvTitle.setText(title);
        tvValue.setText(value);
        ivNext.setImageResource(image);

    }
}
