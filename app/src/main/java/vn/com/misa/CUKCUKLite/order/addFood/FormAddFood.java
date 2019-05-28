package vn.com.misa.CUKCUKLite.order.addFood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
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
import vn.com.misa.CUKCUKLite.order.editFood.FoodModel;
import vn.com.misa.CUKCUKLite.order.editFood.FoodPresenter;
import vn.com.misa.CUKCUKLite.order.editFood.FormEditFood;
import vn.com.misa.CUKCUKLite.order.editFood.IFoodContract;
import vn.com.misa.CUKCUKLite.order.selectUnit.FormSelectUnit;
import vn.com.misa.CUKCUKLite.order.selectUnit.IUnitContract;
import vn.com.misa.CUKCUKLite.order.selectUnit.UnitModel;
import vn.com.misa.CUKCUKLite.order.selectUnit.UnitPresenter;
import vn.com.misa.CUKCUKLite.util.ConstantKey;
import vn.com.misa.CUKCUKLite.util.helper.Converter;

/**
 * Lớp thêm món ăn
 * @Create_by: trand
 * @Date: 5/28/2019
 */
public class FormAddFood extends AppCompatActivity implements IUnitContract.IUnitView, IFoodContract.IFoodView {

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
    @BindView(R.id.frmColor)
    FrameLayout frmColor;
    @BindView(R.id.frmIcon)
    FrameLayout frmIcon;
    @BindView(R.id.tvFoodName)
    TextView tvFoodName;
    @BindView(R.id.etFoodName)
    EditText etFoodName;
    @BindView(R.id.tvLabelUnit)
    TextView tvLabelUnit;
    @BindView(R.id.ivSelectUnit)
    ImageView ivSelectUnit;

    IUnitContract.IUnitPresenter iUnitPresenter;
    IFoodContract.IFoodPresenter iFoodPresenter;
    final int REQUEST_CODE = 0;
    final int RESULT_CODE = 1;
    final int FIRST_UNIT = 0;
    Unit unitSelected; // đơn vị đã chọn, mặc định là đầu tiên

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_form_add_food);
            ButterKnife.bind(this);
            initPresenter();
            initToolBar();
            initView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm khởi tạo presenter
     * @Create_by: trand
     * @Date: 5/28/2019
     */
    private void initPresenter() {
        iUnitPresenter = new UnitPresenter(new UnitModel(this),this);
        iFoodPresenter = new FoodPresenter(new FoodModel(this),this);
        iUnitPresenter.loadAllUnit();
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
        tvUnit.setText(unitSelected.getUnitName());
    }

    /**
     * Hàm ánh xạ toolbar
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    private void initToolBar() {
        try {
            tvTitleToolbar.setText(getString(R.string.title_add_order));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm xử lý các sự kiên
     * @param
     * @return
     * @created_by tdcong
     * @date 5/23/2019
     */
    @OnClick({R.id.ivBack, R.id.tv_saveFood, R.id.tvUnit, R.id.ivSelectUnit,R.id.btnSave})
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
                    saveNewFood();
                    finish();
                    break;
                case R.id.btnSave:
                    saveNewFood();
                    finish();
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm thêm mới món ăn
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: 
     * @Return: 
     */
    private void saveNewFood() {
        try {
            String foodName = etFoodName.getText().toString().trim();
            long foodPrice = Converter.convertToLong(etPrice.getText().toString().trim());
            int unitID = unitSelected.getUnitID();
            String backgroundColor = "";
            String foodIcon = "ic_default";
            Food food = new Food(1, foodName, foodPrice, unitID, backgroundColor, foodIcon, ConstantKey.SELLING);
            iFoodPresenter.saveNewFood(food);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm gửi đơn vị đã chọn
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    private void sendUnitSelected() {
        Intent intent = new Intent(this, FormSelectUnit.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantKey.KEY_SEND_UNIT, unitSelected);
        bundle.putString(ConstantKey.KEY_SCREEN,ConstantKey.SCREEN_ADD_FOOD);
        intent.putExtra(ConstantKey.KEY_SEND_UNIT, bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Hàm trả về kết quả đơn vị đã chọn bên màn hình đơn vị
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
        unitSelected = unitList.get(FIRST_UNIT);
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
