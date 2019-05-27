package vn.com.misa.CUKCUKLite.order.editFood;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.model.Food;
import vn.com.misa.CUKCUKLite.model.Unit;
import vn.com.misa.CUKCUKLite.order.selectUnit.FormSelectUnit;
import vn.com.misa.CUKCUKLite.order.selectUnit.IUnitContract;
import vn.com.misa.CUKCUKLite.order.selectUnit.UnitModel;
import vn.com.misa.CUKCUKLite.order.selectUnit.UnitPresenter;
import vn.com.misa.CUKCUKLite.util.AppUtil;
import vn.com.misa.CUKCUKLite.util.ConstantKey;
import vn.com.misa.CUKCUKLite.util.helper.Converter;

/**
 * Create by: trand
 * Date: 5/26/2019
 */
public class FormEditFood extends AppCompatActivity implements IUnitContract.IUnitView {

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
    @BindView(R.id.etPrice)
    EditText etPrice;
    @BindView(R.id.tvUnit)
    TextView tvUnit;
    @BindView(R.id.frmBackgroundColor)
    FrameLayout frmBackgroundColor;
    @BindView(R.id.frmBackgroundIcon)
    FrameLayout frmBackgroundIcon;
    @BindView(R.id.cbStatus)
    CheckBox cbStatus;
    @BindView(R.id.tvFoodName)
    TextView tvFoodName;
    @BindView(R.id.etFoodName)
    EditText etFoodName;
    @BindView(R.id.tvLabelUnit)
    TextView tvLabelUnit;

    IUnitContract.IUnitPresenter iUnitPresenter;
    Food detailFood;
    Unit unitSelected;

    /**
     * @param
     * @return
     * @created_by tdcong
     * @date 5/23/2019
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_form_edit_food);
            ButterKnife.bind(this);
            initPresenter();
            initToolBar();
            getDetailFood();
            initView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    private void initPresenter() {
        try {
            iUnitPresenter = new UnitPresenter(new UnitModel(this), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    private void getDetailFood() {
        try {
            Bundle bundle = getIntent().getExtras();
            detailFood = (Food) bundle.getSerializable(ConstantKey.KEY_SEND_FOOD);
            setDetailFood(detailFood);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    private void setDetailFood(Food detailFood) {
        try {
            unitSelected = iUnitPresenter.getUnit(detailFood.getUnitID());
            String foodName = detailFood.getFoodName();
            String foodPrice = Converter.convertToCurrency(detailFood.getFoodPrice());
            String unitName = unitSelected.getUnitName();
            String foodBackgroundColor = detailFood.getColorBackground();
            String foodBackgroundIcon = detailFood.getColorBackground();
            boolean Status = Converter.convertStatusOrder(detailFood.getFoodStatus());
            etFoodName.setText(foodName);
            etPrice.setText(foodPrice);
            tvUnit.setText(unitName);
            frmBackgroundColor.setBackground(AppUtil.createCircleBackground(foodBackgroundColor));
            frmBackgroundIcon.setBackground(AppUtil.createCircleBackground(foodBackgroundIcon));
            cbStatus.setChecked(Status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param
     * @return
     * @created_by tdcong
     * @date 5/23/2019
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initView() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvFoodName.setText(Html.fromHtml(getString(R.string.tv_food_name), Html.FROM_HTML_MODE_COMPACT));
                tvLabelUnit.setText(Html.fromHtml(getString(R.string.tv_label_unit), Html.FROM_HTML_MODE_COMPACT));
            } else {
                tvFoodName.setText(Html.fromHtml(getString(R.string.tv_food_name)));
                tvLabelUnit.setText(Html.fromHtml(getString(R.string.tv_label_unit)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param
     * @return
     * @created_by tdcong
     * @date 5/23/2019
     */
    private void initToolBar() {
        try {
            tvTitleToolbar.setText(getString(R.string.title_edit_order));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm xử lý bắt sự kiên view
     *
     * @param
     * @return
     * @created_by tdcong
     * @date 5/22/2019
     */
    @OnClick({R.id.ivBack, R.id.tv_saveFood, R.id.tvUnit, R.id.cbStatus, R.id.ivSelectUnit})
    public void onViewClicked(View view) {
        try {
            switch (view.getId()) {
                case R.id.ivBack:
                    try {
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.tv_saveFood:
                    break;
                case R.id.tvUnit:
                    try {
                        sendUnitSelected();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.ivSelectUnit:
                    try {
                        sendUnitSelected();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.cbStatus:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: 
     * @Return: 
     */
    private void sendUnitSelected() {
        Intent intent = new Intent(FormEditFood.this, FormSelectUnit.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantKey.KEY_SEND_UNIT,unitSelected);
        intent.putExtra(ConstantKey.KEY_SEND_UNIT,bundle);
        startActivityForResult(intent,0);
    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: 
     * @Return: 
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            Bundle bundle = new Bundle();
            bundle = data.getBundleExtra(ConstantKey.KEY_SEND_UNIT);
            unitSelected = (Unit) bundle.getSerializable(ConstantKey.KEY_SEND_UNIT);
            String unitName = unitSelected.getUnitName();
            tvUnit.setText(unitName);
        }
    }

    @Override
    public void displayUnit(List<Unit> unitList) {

    }

    @Override
    public void saveNewUnitSuccess(Unit newUnit) {

    }

    @Override
    public void saveNewUnitFail() {

    }
}
