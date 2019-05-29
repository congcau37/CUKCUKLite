package vn.com.misa.CUKCUKLite.screen.adddish;

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
import vn.com.misa.CUKCUKLite.model.Dish;
import vn.com.misa.CUKCUKLite.model.Unit;
import vn.com.misa.CUKCUKLite.screen.chooseunit.ChooseActivity;
import vn.com.misa.CUKCUKLite.screen.chooseunit.IChooseUnitContract;
import vn.com.misa.CUKCUKLite.screen.chooseunit.ChooseUnitModel;
import vn.com.misa.CUKCUKLite.screen.chooseunit.ChooseUnitPresenter;
import vn.com.misa.CUKCUKLite.util.ConstantKey;
import vn.com.misa.CUKCUKLite.util.helper.Converter;

/**
 * Lớp thêm món ăn
 * @Create_by: trand
 * @Date: 5/28/2019
 */
public class AddDishActivity extends AppCompatActivity implements IChooseUnitContract.IView, AddDishContract.IView {

    @BindView(R.id.ivBack)
    ImageView imvBack;
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.tvSaveDish)
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
    @BindView(R.id.tvdishName)
    TextView tvDishName;
    @BindView(R.id.etDishName)
    EditText etDishName;
    @BindView(R.id.tvLabelUnit)
    TextView tvLabelUnit;
    @BindView(R.id.ivSelectUnit)
    ImageView ivSelectUnit;

    IChooseUnitContract.IPresenter iPresenterUnit;
    AddDishContract.IPresenter iPresenterDish;
    final int REQUEST_CODE = 0;
    final int RESULT_CODE = 1;
    final int FIRST_UNIT = 0;
    Unit unitSelected; // đơn vị đã chọn, mặc định là đầu tiên

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_form_add_dish);
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
        iPresenterUnit = new ChooseUnitPresenter(new ChooseUnitModel(this),this);
        iPresenterDish = new AddDishPresenter(new AddDishModel(this),this);
        iPresenterUnit.loadAllUnit();
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
                tvDishName.setText(Html.fromHtml(getString(R.string.tv_food_name), Html.FROM_HTML_MODE_COMPACT));
                tvLabelUnit.setText(Html.fromHtml(getString(R.string.tv_label_unit), Html.FROM_HTML_MODE_COMPACT));
            } else {
                tvDishName.setText(Html.fromHtml(getString(R.string.tv_food_name)));
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
    @OnClick({R.id.ivBack, R.id.tvSaveDish, R.id.tvUnit, R.id.ivSelectUnit,R.id.btnSave})
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
                case R.id.tvSaveDish:
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
            String dishName = etDishName.getText().toString().trim();
            long dishPrice = Converter.convertToLong(etPrice.getText().toString().trim());
            int unitID = unitSelected.getUnitID();
            String backgroundColor = "";
            String foodIcon = "ic_default";
            Dish dish = new Dish(1, dishName, dishPrice, unitID, backgroundColor, foodIcon, ConstantKey.SELLING);
            iPresenterDish.saveNewDish(dish);
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
        Intent intent = new Intent(this, ChooseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantKey.KEY_SEND_UNIT, unitSelected);
        bundle.putString(ConstantKey.KEY_SCREEN,ConstantKey.SCREEN_ADD_DISH);
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
    public void saveNewDishSuccess() {

    }

    @Override
    public void saveNewDishFail(String error) {

    }

    @Override
    public void updateDishSuccess() {

    }

    @Override
    public void updateDishFail() {

    }
}
