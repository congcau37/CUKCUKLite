package vn.com.misa.CUKCUKLite.screen.adddish;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.model.Dish;
import vn.com.misa.CUKCUKLite.model.Unit;
import vn.com.misa.CUKCUKLite.screen.chooseunit.ChooseUnitActivity;
import vn.com.misa.CUKCUKLite.screen.chooseunit.ChooseUnitModel;
import vn.com.misa.CUKCUKLite.screen.chooseunit.ChooseUnitPresenter;
import vn.com.misa.CUKCUKLite.screen.chooseunit.IChooseUnitContract;
import vn.com.misa.CUKCUKLite.screen.dialogicon.IconPickerDialog;
import vn.com.misa.CUKCUKLite.util.ConstantKey;
import vn.com.misa.CUKCUKLite.util.helper.Converter;

/**
 * Lớp thêm món ăn
 *
 * @Create_by: trand
 * @Date: 5/28/2019
 */
public class AddDishActivity extends AppCompatActivity implements IChooseUnitContract.IView, IAddDishContract.IView {

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
    @BindView(R.id.frmBackgroundColor)
    FrameLayout frmColor;
    @BindView(R.id.frmBackgroundIcon)
    FrameLayout frmIcon;
    @BindView(R.id.tvdishName)
    TextView tvDishName;
    @BindView(R.id.etDishName)
    EditText etDishName;
    @BindView(R.id.tvLabelUnit)
    TextView tvLabelUnit;
    @BindView(R.id.cbStatus)
    CheckBox cbStatus;
    @BindView(R.id.llStatus)
    LinearLayout llStatus;
    @BindView(R.id.ivSelectUnit)
    ImageView ivSelectUnit;
    @BindView(R.id.ivDish)
    ImageView ivDish;
    @BindView(R.id.btnDelete)
    Button btnDelete;
    @BindView(R.id.btnSave)
    Button btnSave;

    IChooseUnitContract.IPresenter iPresenterUnit;
    IAddDishContract.IPresenter iPresenterDish;
    final int REQUEST_CODE = 0;
    final int RESULT_CODE = 1;
    final int FIRST_UNIT = 0;
    Unit unitSelected; // đơn vị đã chọn, mặc định là đầu tiên

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_form_add_edit_dish);
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
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     */
    private void initPresenter() {
        iPresenterUnit = new ChooseUnitPresenter(new ChooseUnitModel(this), this, this);
        iPresenterDish = new AddDishPresenter(new AddDishModel(this), this, this);
        iPresenterUnit.loadAllUnit();
    }

    /**
     * Hàm ánh xạ toolbar
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    private void initToolBar() {
        try {
            tvTitleToolbar.setText(getString(R.string.add_dish));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm ánh xạ view
     *
     * @param
     * @return
     * @created_by tdcong
     * @date 5/23/2019
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initView() {
        try {
            llStatus.setVisibility(View.INVISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvDishName.setText(Html.fromHtml(getString(R.string.dish_name), Html.FROM_HTML_MODE_COMPACT));
                tvLabelUnit.setText(Html.fromHtml(getString(R.string.label_unit), Html.FROM_HTML_MODE_COMPACT));
            } else {
                tvDishName.setText(Html.fromHtml(getString(R.string.dish_name)));
                tvLabelUnit.setText(Html.fromHtml(getString(R.string.label_unit)));
            }

            etPrice.setText("0");
            etPrice.addTextChangedListener(new TextWatcher() {
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if (etPrice.getText().toString().equals(ConstantKey.VALUE_EMPTY)) {
                        etPrice.setText(ConstantKey.VALUE_ZERO);
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
     * Hàm xử lý các sự kiên
     *
     * @param
     * @return
     * @created_by tdcong
     * @date 5/23/2019
     */
    @OnClick({R.id.ivBack, R.id.tvSaveDish, R.id.tvUnit, R.id.ivSelectUnit, R.id.btnSave, R.id.frmBackgroundIcon})
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
                case R.id.btnSave:
                    if (validateForm())
                        saveNewDish();
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
                case R.id.frmBackgroundIcon:
                    try {
                        showIconPickerDialog();
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
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    private void saveNewDish() {
        try {
            String dishName = etDishName.getText().toString().trim();
            long dishPrice = Converter.convertToLong(etPrice.getText().toString().trim());
            int unitID;
            unitID = getUnitSelected().getUnitID();
            String backgroundColor = ConstantKey.COLOR_DEFAULT;
            String foodIcon = ConstantKey.PACKAGE_ICON_DEFAULT;
            Dish dish = new Dish(ConstantKey.VALUE_ZERO, dishName, dishPrice, unitID, backgroundColor, foodIcon, ConstantKey.SELLING);
            iPresenterDish.saveNewDish(dish);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích Methob:
     *
     * @created_by tdcong
     * @date 5/31/2019
     */
    private boolean validateForm() {
        String error = ConstantKey.VALUE_EMPTY;
        String dishName = etDishName.getText().toString().trim();
        if (dishName.equals(ConstantKey.VALUE_EMPTY)) {
            error = getString(R.string.not_empty_dish_name);
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Hàm gửi đơn vị đã chọn
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    private void sendUnitSelected() {
        Intent intent = new Intent(this, ChooseUnitActivity.class);
        Bundle bundle = new Bundle();
        if (getUnitSelected() != ConstantKey.UNIT_NO_SELECT) {
            bundle.putSerializable(ConstantKey.KEY_SEND_UNIT, getUnitSelected());
        } else {
            bundle.putSerializable(ConstantKey.KEY_SEND_UNIT, null);
        }
        intent.putExtra(ConstantKey.KEY_SEND_UNIT, bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Mục đích Methob:
     *
     * @created_by tdcong
     * @date 5/31/2019
     */
    public Unit getUnitSelected() {
        return unitSelected;
    }

    /**
     * Hàm trả về kết quả đơn vị đã chọn bên màn hình đơn vị
     *
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
            if (bundle != null) {
                unitSelected = (Unit) bundle.getSerializable(ConstantKey.KEY_SEND_UNIT);
                if (getUnitSelected() == ConstantKey.UNIT_NO_SELECT) {
                    tvUnit.setHint(getString(R.string.select_unit));
                }
                tvUnit.setText(getUnitSelected().getUnitName());
            }
        }
    }

    public void showIconPickerDialog(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        IconPickerDialog iconPickerDialog = new IconPickerDialog();
        iconPickerDialog.show(fragmentManager,ConstantKey.DIALOG);
    }

    /**
     * Mục đích Methob:
     * @created_by tdcong
     * @date 5/31/2019
     * @param:
     * @return:
     */
    @Override
    public void displayUnit(List<Unit> unitList) {
        try {
            unitSelected = unitList.get(FIRST_UNIT);
            if (unitSelected == ConstantKey.UNIT_NO_SELECT) {
                tvUnit.setHint(getString(R.string.select_unit));
            } else {
                tvUnit.setText(unitSelected.getUnitName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveNewUnitSuccess(Unit newUnit) {

    }

    @Override
    public void saveNewUnitFail(String error) {

    }

    @Override
    public void updateUnitSuccess(Unit unit) {

    }

    @Override
    public void updateUnitFail(String error) {

    }

    @Override
    public void deleteUnitSuccess(int id) {

    }

    @Override
    public void deleteUnitFail() {

    }

    @Override
    public void saveNewDishSuccess() {
        Intent intentBroadCast = new Intent(ConstantKey.ACTION_NOTIFY_DATA);
        sendBroadcast(intentBroadCast);
        finish();
    }

    @Override
    public void saveNewDishFail(String error) {

    }
}
