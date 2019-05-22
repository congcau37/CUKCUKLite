package vn.com.misa.CUKCUKLite.order.addFood;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.com.misa.CUKCUKLite.R;

/**
 * @created_by tdcong
 * @date 5/17/2019
 */
public class FormAddFood extends AppCompatActivity {


    @BindView(R.id.ivBack)
    ImageView imvBack;
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.tv_saveFood)
    TextView tvSaveFood;
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.foodName)
    EditText foodName;
    @BindView(R.id.tvPrice)
    EditText tvPrice;
    @BindView(R.id.tvUnit)
    TextView tvUnit;
    @BindView(R.id.frmColor)
    FrameLayout frmColor;
    @BindView(R.id.frmIcon)
    FrameLayout frmIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_form_add_food);
            ButterKnife.bind(this);
            initToolBar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm xử lý sự kiên thanh toolbar
     *
     * @param
     * @return
     */
    private void initToolBar() {
        try {
            tvTitleToolbar.setText(getString(R.string.title_add_order));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm xử lý các sự kiện view
     * Create by: trand
     * Date: 5/22/2019
     */
    @OnClick({R.id.ivBack, R.id.tv_saveFood, R.id.tvUnit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tv_saveFood:
                break;
            case R.id.tvUnit:
                break;
        }
    }

}
