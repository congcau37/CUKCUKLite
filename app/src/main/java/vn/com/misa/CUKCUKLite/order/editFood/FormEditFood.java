package vn.com.misa.CUKCUKLite.order.editFood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
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
 * Lớp cập nhật món ăn
 * @Create_by: trand
 * @Date: 5/28/2019
 * @Param:
 * @Return:
 */
public class FormEditFood extends AppCompatActivity implements IUnitContract.IUnitView, IFoodContract.IFoodView {

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
    @BindView(R.id.ivSelectUnit)
    ImageView ivSelectUnit;
    @BindView(R.id.ivFood)
    ImageView ivFood;

    IUnitContract.IUnitPresenter iUnitPresenter;
    IFoodContract.IFoodPresenter iFoodPresenter;
    Food detailFood;
    Unit unitSelected;
    int foodID;
    String foodStatus = "";
    final int REQUEST_CODE = 0;
    final int RESULT_CODE = 1;
    @BindView(R.id.btnDelete)
    Button btnDelete;
    @BindView(R.id.btnSave)
    Button btnSave;

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
     * Hàm khỏi tạo presenter
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    private void initPresenter() {
        try {
            iUnitPresenter = new UnitPresenter(new UnitModel(this), this);
            iFoodPresenter = new FoodPresenter(new FoodModel(this), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm lấy ra chi tiết mon ăn
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
     * Hàm hiển thị chi tiết món ăn
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: Food
     * @Return:
     */
    private void setDetailFood(Food detailFood) {
        try {
            unitSelected = iUnitPresenter.getUnit(detailFood.getUnitID());
            foodID = detailFood.getFoodID();
            String foodName = detailFood.getFoodName();
            String foodPrice = Converter.convertToCurrency(detailFood.getFoodPrice());
            String unitName = unitSelected.getUnitName();
            String iconName = detailFood.getFoodIcon();
            String foodBackgroundColor = detailFood.getColorBackground();
            String foodBackgroundIcon = detailFood.getColorBackground();
            boolean Status = Converter.convertStatusOrder(detailFood.getFoodStatus());
            etFoodName.setText(foodName);
            etPrice.setText(foodPrice);
            tvUnit.setText(unitName);
            if (!iconName.equals("")) {
                try {
                    InputStream ims = getAssets().open("icondefault/" + iconName + ".png");
                    Drawable d = Drawable.createFromStream(ims, null);
                    ivFood.setImageDrawable(d);
                    ims.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (!foodBackgroundColor.equals("")) {
                try {
                    frmBackgroundColor.setBackground(AppUtil.createCircleBackground(foodBackgroundColor));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            frmBackgroundIcon.setBackground(AppUtil.createCircleBackground(foodBackgroundIcon));
            cbStatus.setChecked(Status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm ánh xạ view
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

            if (detailFood.getFoodStatus().equals(ConstantKey.SELLING)) {
                foodStatus = ConstantKey.SELLING;
            } else {
                foodStatus = ConstantKey.STOP_SALE;
            }

            cbStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked == false) {
                        foodStatus = ConstantKey.SELLING;
                    } else {
                        foodStatus = ConstantKey.STOP_SALE;
                    }
                }
            });

            etPrice.addTextChangedListener(new TextWatcher() {
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if(etPrice.getText().toString().equals("") ) {
                        etPrice.setText("0");
                    }
                }
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }
                public void afterTextChanged(Editable s) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm ánh xạ toolbar
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
     * @param
     * @return
     * @created_by tdcong
     * @date 5/22/2019
     */
    @OnClick({R.id.ivBack, R.id.tv_saveFood, R.id.tvUnit, R.id.cbStatus, R.id.ivSelectUnit, R.id.btnSave, R.id.btnDelete})
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
                    updateFood();
                    finish();
                    break;
                case R.id.btnSave:
                    updateFood();
                    finish();
                    break;
                case R.id.btnDelete:
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
     * Hàm cập nhật món ăn
     * @Create_by: trand
     * @Date: 5/29/2019
     * @Param:
     * @Return:
     */
    private void updateFood() {
        try {
            String foodName = etFoodName.getText().toString().trim();
            long foodPrice = Converter.convertToLong(etPrice.getText().toString().trim());
            int unitID = unitSelected.getUnitID();
            @SuppressLint("ResourceType") String backgroundColor = getString(R.color.color_primary);
            String foodIcon = detailFood.getFoodIcon();
            detailFood.setFoodName(foodName);
            detailFood.setFoodPrice(foodPrice);
            detailFood.setUnitID(unitID);
            detailFood.setColorBackground(backgroundColor);
            detailFood.setFoodIcon(foodIcon);
            detailFood.setFoodStatus(foodStatus);
            iFoodPresenter.updateFood(detailFood);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm gửi đơn vị tính đã chọn
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    private void sendUnitSelected() {
        Intent intent = new Intent(FormEditFood.this, FormSelectUnit.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantKey.KEY_SEND_UNIT, unitSelected);
        bundle.putString(ConstantKey.KEY_SCREEN, ConstantKey.SCREEN_EDIT_FOOD);
        intent.putExtra(ConstantKey.KEY_SEND_UNIT, bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Hàm trả về đơn vị tính đã chọn từ màn hình đơn vị tính
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: requestCode, resultCode, data
     * @Return:
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_CODE) {
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
    public void saveNewUnitFail(String error) {

    }

    @Override
    public void updateUnitSuccess() {

    }

    @Override
    public void updateUnitFail() {

    }

    @Override
    public void deleteUnitSuccess() {

    }

    @Override
    public void deleteUnitFail() {

    }

    @Override
    public void saveNewFoodSuccess() {

    }

    @Override
    public void saveNewFoodFail(String error) {

    }

    @Override
    public void updateFoodSuccess() {

    }

    @Override
    public void updateFoodFail() {

    }
}
