package vn.com.misa.CUKCUKLite.order.AddFood;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.com.misa.CUKCUKLite.R;

/**
 * @created_by tdcong
 * @date 5/17/2019
 */
public class AddFood extends AppCompatActivity {

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
            setContentView(R.layout.activity_add_food);
            ButterKnife.bind(this);
            initToolBar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm
     *
     * @param
     * @return
     */
    private void initToolBar() {
        toolbar.setTitle(R.string.add_order);
        tvTitleToolbar.setText(toolbar.getTitle());
    }

    @OnClick({R.id.imvBack, R.id.tv_saveFood})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imvBack:
                finish();
                break;
            case R.id.tv_saveFood:
                Toast.makeText(this, "Cất thành công!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
