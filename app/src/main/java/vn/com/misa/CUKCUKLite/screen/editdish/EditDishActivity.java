package vn.com.misa.CUKCUKLite.screen.editdish;

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
import android.widget.Toast;

import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.model.Dish;
import vn.com.misa.CUKCUKLite.model.Unit;
import vn.com.misa.CUKCUKLite.screen.chooseunit.ChooseUnitActivity;
import vn.com.misa.CUKCUKLite.screen.chooseunit.IChooseUnitContract;
import vn.com.misa.CUKCUKLite.screen.chooseunit.ChooseUnitModel;
import vn.com.misa.CUKCUKLite.screen.chooseunit.ChooseUnitPresenter;
import vn.com.misa.CUKCUKLite.util.AppUtil;
import vn.com.misa.CUKCUKLite.util.ConstantKey;
import vn.com.misa.CUKCUKLite.util.helper.Converter;

/**
 * Lớp cập nhật món ăn
 *
 * @Create_by: trand
 * @Date: 5/28/2019
 * @Param:
 * @Return:
 */
public class EditDishActivity extends AppCompatActivity implements IChooseUnitContract.IView, IEditDishContract.IView {

    @BindView(R.id.ivBack)
    ImageView imvBack;
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.tvSaveDish)
    TextView tvSaveDish;
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
    @BindView(R.id.tvdishName)
    TextView tvFoodName;
    @BindView(R.id.etDishName)
    EditText etDishName;
    @BindView(R.id.tvLabelUnit)
    TextView tvLabelUnit;
    @BindView(R.id.ivSelectUnit)
    ImageView ivSelectUnit;
    @BindView(R.id.ivDish)
    ImageView ivDish;
    @BindView(R.id.btnDelete)
    Button btnDelete;
    @BindView(R.id.btnSave)
    Button btnSave;

    IChooseUnitContract.IPresenter iPresenterUnit;
    IEditDishContract.IPresenter iPresenterDish;
    Dish detailDish;
    Unit unitSelected;
    int foodID;
    String foodStatus = "";
    final int REQUEST_CODE = 0;
    final int RESULT_CODE = 1;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_form_add_edit_dish);
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
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    private void initPresenter() {
        try {
            iPresenterUnit = new ChooseUnitPresenter(new ChooseUnitModel(this), this, this);
            iPresenterDish = new EditDishPresenter(new EditDishModel(this), this, this);
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvFoodName.setText(Html.fromHtml(getString(R.string.dish_name), Html.FROM_HTML_MODE_COMPACT));
                tvLabelUnit.setText(Html.fromHtml(getString(R.string.label_unit), Html.FROM_HTML_MODE_COMPACT));
            } else {
                tvFoodName.setText(Html.fromHtml(getString(R.string.dish_name)));
                tvLabelUnit.setText(Html.fromHtml(getString(R.string.label_unit)));
            }

            if (detailDish.getDishStatus().equals(ConstantKey.SELLING)) {
                foodStatus = ConstantKey.SELLING;
            } else {
                foodStatus = ConstantKey.STOP_SALE;
            }

            cbStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!isChecked) {
                        foodStatus = ConstantKey.SELLING;
                    } else {
                        foodStatus = ConstantKey.STOP_SALE;
                    }
                }
            });

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
    }

    /**
     * Hàm ánh xạ toolbar
     *
     * @param
     * @return
     * @created_by tdcong
     * @date 5/23/2019
     */
    private void initToolBar() {
        try {
            tvTitleToolbar.setText(getString(R.string.edit_dish));
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
    @OnClick({R.id.ivBack, R.id.tvSaveDish, R.id.tvUnit, R.id.cbStatus, R.id.ivSelectUnit, R.id.btnSave, R.id.btnDelete})
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
                        updateDish();
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
     * Hàm lấy ra chi tiết mon ăn
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    private void getDetailFood() {
        try {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                detailDish = (Dish) bundle.getSerializable(ConstantKey.KEY_SEND_DISH);
                setDetailDish(detailDish);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm hiển thị chi tiết món ăn
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: Dish
     * @Return:
     */
    private void setDetailDish(Dish detailDish) {
        try {
            String unitName = ConstantKey.VALUE_EMPTY;
            unitSelected = iPresenterUnit.getUnit(detailDish.getUnitID());
            foodID = detailDish.getDishID();
            String dishName = detailDish.getDishName();
            String dishPrice = NumberFormat.getIntegerInstance(Locale.GERMAN).format(detailDish.getDishPrice());
            if (unitSelected == null) {
                unitSelected = ConstantKey.UNIT_NO_SELECT;
                tvUnit.setText(getString(R.string.select_unit));
            } else {
                unitName = unitSelected.getUnitName();
                tvUnit.setText(unitName);
            }
            String iconName = detailDish.getDishIcon();
            String foodBackgroundColor = detailDish.getColorBackground();
            String foodBackgroundIcon = detailDish.getColorBackground();
            boolean Status = Converter.convertStatusMenu(detailDish.getDishStatus());
            etDishName.setText(dishName);
            etPrice.setText(dishPrice);
            InputStream ims = getAssets().open(ConstantKey.PACKAGE_ICON + iconName + ConstantKey.TAIL_ICON);
            Drawable d = Drawable.createFromStream(ims, null);
            ivDish.setImageDrawable(d);
            ims.close();
            frmBackgroundColor.setBackground(AppUtil.createCircleBackground(foodBackgroundColor));
            frmBackgroundIcon.setBackground(AppUtil.createCircleBackground(foodBackgroundIcon));
            cbStatus.setChecked(Status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm cập nhật món ăn
     *
     * @Create_by: trand
     * @Date: 5/29/2019
     * @Param:
     * @Return:
     */
    private void updateDish() {
        try {
            if (validateForm()) {
                String foodName = etDishName.getText().toString().trim();
                long foodPrice = Converter.convertToLong(etPrice.getText().toString().trim());
                if (unitSelected == null) {
                    detailDish.setUnitID(ConstantKey.UNIT_NO_SELECT.getUnitID());
                } else {
                    detailDish.setUnitID(unitSelected.getUnitID());
                }
                @SuppressLint("ResourceType") String backgroundColor = getString(R.color.color_primary);
                String foodIcon = detailDish.getDishIcon();
                detailDish.setDishName(foodName);
                detailDish.setDishID(detailDish.getDishID());
                detailDish.setDishPrice(foodPrice);
                detailDish.setColorBackground(backgroundColor);
                detailDish.setDishIcon(foodIcon);
                detailDish.setDishStatus(foodStatus);
                iPresenterDish.updateDish(detailDish);
            }
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
     * Hàm gửi đơn vị tính đã chọn
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    private void sendUnitSelected() {
        Intent intent = new Intent(EditDishActivity.this, ChooseUnitActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantKey.KEY_SEND_UNIT, unitSelected);
        intent.putExtra(ConstantKey.KEY_SEND_UNIT, bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * Hàm trả về đơn vị tính đã chọn từ màn hình đơn vị tính
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: requestCode, resultCode, data
     * @Return:
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == REQUEST_CODE && resultCode == RESULT_CODE) {
                Bundle bundle = new Bundle();
                bundle = data.getBundleExtra(ConstantKey.KEY_SEND_UNIT);
                unitSelected = (Unit) bundle.getSerializable(ConstantKey.KEY_SEND_UNIT);
                if (unitSelected == ConstantKey.UNIT_NO_SELECT) {
                    tvUnit.setHint(getString(R.string.select_unit));
                } else {
                    tvUnit.setText(unitSelected.getUnitName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    public void updateDishSuccess() {
        Intent intentBroadCast = new Intent(ConstantKey.ACTION_NOTIFY_DATA);
        sendBroadcast(intentBroadCast);
        finish();
    }

    @Override
    public void updateDishFail() {

    }
}
