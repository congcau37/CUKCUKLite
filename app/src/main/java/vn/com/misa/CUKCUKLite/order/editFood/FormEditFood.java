package vn.com.misa.CUKCUKLite.order.editFood;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.com.misa.CUKCUKLite.R;

public class FormEditFood extends AppCompatActivity {


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
    @BindView(R.id.cbStatus)
    CheckBox cbStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_edit_food);
        ButterKnife.bind(this);
        initToolBar();
    }

    private void initToolBar() {
        tvTitleToolbar.setText(getString(R.string.title_edit_order));
    }

    /**
     * Hàm xử lý bắt sự kiên view
     *
     * @param
     * @return
     * @created_by tdcong
     * @date 5/22/2019
     */
    @OnClick({R.id.ivBack, R.id.tv_saveFood, R.id.tvUnit, R.id.cbStatus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tv_saveFood:
                break;
            case R.id.tvUnit:
                break;
            case R.id.cbStatus:
                break;
        }
    }
}
