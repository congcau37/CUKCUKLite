package vn.com.misa.CUKCUKLite.order.editorder;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.com.misa.CUKCUKLite.R;

public class EditOrder extends AppCompatActivity {

    @BindView(R.id.imvBack)
    ImageView imvBack;
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.tv_saveFood)
    TextView tvSaveFood;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
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
        setContentView(R.layout.activity_edit_order);
        ButterKnife.bind(this);
        initToolBar();
    }

    private void initToolBar() {
        toolbar.setTitle(R.string.edit_order);
        tvTitleToolbar.setText(toolbar.getTitle());
    }

    /**
     * Hàm xử lý bắt sự kiên view
     * @created_by tdcong
     * @date 5/22/2019
     * @param
     * @return
     */
    @OnClick({R.id.imvBack, R.id.tv_saveFood, R.id.tvPrice, R.id.tvUnit, R.id.cbStatus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imvBack:
                finish();
                break;
            case R.id.tv_saveFood:
                break;
            case R.id.tvPrice:
                break;
            case R.id.tvUnit:
                break;
            case R.id.cbStatus:
                break;
        }
    }
}
