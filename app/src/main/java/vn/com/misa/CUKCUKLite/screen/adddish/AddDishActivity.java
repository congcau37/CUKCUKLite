package vn.com.misa.CUKCUKLite.screen.adddish;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.thebluealliance.spectrum.SpectrumDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.model.Dish;
import vn.com.misa.CUKCUKLite.model.Unit;
import vn.com.misa.CUKCUKLite.screen.caculator.CalculatorFragment;
import vn.com.misa.CUKCUKLite.screen.chooseunit.ChooseIUnitActivity;
import vn.com.misa.CUKCUKLite.screen.chooseunit.ChooseUnitModel;
import vn.com.misa.CUKCUKLite.screen.chooseunit.ChooseUnitPresenter;
import vn.com.misa.CUKCUKLite.screen.chooseunit.IChooseUnitContract;
import vn.com.misa.CUKCUKLite.screen.dialogicon.IconPickerDialog;
import vn.com.misa.CUKCUKLite.util.AppUtil;
import vn.com.misa.CUKCUKLite.util.ConstantKey;
import vn.com.misa.CUKCUKLite.util.helper.Converter;

import static vn.com.misa.CUKCUKLite.util.ConstantKey.REQUEST_CODE;
import static vn.com.misa.CUKCUKLite.util.ConstantKey.RESULT_CODE;

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
    Unit unitSelected; // đơn vị đã chọn, mặc định là đầu tiên
    private Dish mDish = new Dish();
    private int selectedColor;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_edit_dish);
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
        //gọi hàm load đơn vị
        iPresenterUnit.loadAllUnit();
    }

    /**
     * Hàm ánh xạ toolbar
     *
     * @Create_by: trand
     * @Date: 5/28/2019
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

            tvUnit.setText(unitSelected.getUnitName());
            btnDelete.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm xử lý các sự kiên onclick
     *
     * @param
     * @return
     * @created_by tdcong
     * @date 5/23/2019
     */
    @OnClick({R.id.ivBack, R.id.tvSaveDish, R.id.tvUnit, R.id.ivSelectUnit, R.id.btnSave, R.id.frmBackgroundIcon, R.id.frmBackgroundColor,R.id.etPrice})
    public void onViewClicked(View view) {
        try {
            switch (view.getId()) {
                //trở về
                case R.id.ivBack:
                    try {
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                //lưu mới món
                case R.id.tvSaveDish:
                case R.id.btnSave:
                    if (validateFormAddDish())
                        saveNewDish();
                    break;
                //chọn đơn vị
                case R.id.tvUnit:
                case R.id.ivSelectUnit:
                    try {
                        sendUnitSelected();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                    //chọn màu nền
                case R.id.frmBackgroundIcon:
                    try {
                        showIconPickerDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                    //chọn icon
                case R.id.frmBackgroundColor:
                    try {
                        showDialogPickColor();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.etPrice:
                    try {
                        showDialogCalculator();
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
     */
    private void saveNewDish() {
        try {
            int unitID;
            String dishName = etDishName.getText().toString().trim();
            long dishPrice = Converter.getNumberInput(etPrice.getText().toString().trim());
            unitID = getUnitSelected().getUnitID();
            String backgroundColor = mDish.getColorBackground();
            String dishIcon = mDish.getDishIcon();
            //không chọn màu và icon sẽ lấy mặc định
            if (backgroundColor == null) {
                backgroundColor = ConstantKey.COLOR_DEFAULT;
            } else {
                backgroundColor = mDish.getColorBackground();
            }
            if (dishIcon == null) {
                dishIcon = ConstantKey.ICON_DEFAULT;
            } else {
                dishIcon = mDish.getDishIcon();
            }
            Dish dish = new Dish(ConstantKey.VALUE_ZERO, dishName, dishPrice, unitID, backgroundColor, dishIcon, ConstantKey.SELLING);
            //gọi hàm tạo mới món
            iPresenterDish.saveNewDish(dish);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích Methob: Kiểm tra form khi người dung nhập
     *
     * @created_by tdcong
     * @date 5/31/2019
     */
    private boolean validateFormAddDish() {
        try {
            String error = ConstantKey.VALUE_EMPTY;
            String dishName = etDishName.getText().toString().trim();
            if (dishName.equals(ConstantKey.VALUE_EMPTY)) {
                error = getString(R.string.not_empty_dish_name);
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Hàm gửi đơn vị đã chọn sang màn hình đơn vị tính
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     */
    private void sendUnitSelected() {
        try {
            Intent intent = new Intent(this, ChooseIUnitActivity.class);
            Bundle bundle = new Bundle();
            //kiểm tra đơn vị đã chọn để gửi sang màn hình đơn vị tính
            if (getUnitSelected() != ConstantKey.UNIT_NO_SELECT) {
                bundle.putSerializable(ConstantKey.KEY_SEND_UNIT, getUnitSelected());
            } else {
                bundle.putSerializable(ConstantKey.KEY_SEND_UNIT, null);
            }
            intent.putExtra(ConstantKey.KEY_SEND_UNIT, bundle);
            startActivityForResult(intent, REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm trả về kết quả đơn vị đã chọn bên màn hình đơn vị tính
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: requestCode, resultCode, data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == REQUEST_CODE && resultCode == RESULT_CODE) {
                Bundle bundle = new Bundle();
                bundle = data.getBundleExtra(ConstantKey.KEY_SEND_UNIT);
                //kiểm tra và lấy ra đơn vị đã chọn
                if (bundle != null) {
                    unitSelected = (Unit) bundle.getSerializable(ConstantKey.KEY_SEND_UNIT);
                    if (getUnitSelected() == ConstantKey.UNIT_NO_SELECT) {
                        tvUnit.setHint(getString(R.string.select_unit));
                    }
                    //hiển thị đơn vị
                    tvUnit.setText(getUnitSelected().getUnitName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích Methob:
     *
     * @created_by tdcong
     * @date 6/3/2019
     */
    public void setIcon(String icon) {
        try {
            Glide.with(this).load(iPresenterDish.getBitmapFromAssets(this, icon)).into(ivDish);
            mDish.setDishIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm hiển thị máy tính
     * @Create_by: trand
     * @Date: 6/4/2019
     */
    public void showDialogCalculator(){
        FragmentManager fm = getSupportFragmentManager();
        CalculatorFragment calculatorFragment = CalculatorFragment.createInstance(ConstantKey.VALUE_ZERO+"", new CalculatorFragment.IOnClickDone() {
            @Override
            public void onClickDone(long price, String priceShow) {
                setPrice(price, priceShow);
            }
        });
        calculatorFragment.show(fm, getString(R.string.dialog_calculator));
    }

    /**
     * Mục đích Methob: Hiển thị dialog chọn icon
     *
     * @created_by tdcong
     * @date 6/3/2019
     */
    public void showIconPickerDialog() {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            IconPickerDialog iconPickerDialog = new IconPickerDialog(this);
            iconPickerDialog.show(fragmentManager, ConstantKey.DIALOG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích Methob:
     *
     * @created_by tdcong
     * @date 6/3/2019
     */
    void showDialogPickColor() {
        try {
            final SpectrumDialog dialog = new SpectrumDialog.Builder(this)
                    .setTitle(getString(R.string.pick_color))
                    .setSelectedColor(selectedColor != 0 ? selectedColor : ConstantKey.COLOR_DEF)
                    .setColors(R.array.arr_colors)
//                    .setFixedColumnCount(4)
                    .setOnColorSelectedListener(new SpectrumDialog.OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(boolean positiveResult, @ColorInt int color) {
                            if (positiveResult) {
                                try {
                                    frmColor.setBackground(AppUtil.setCircleBackground(color, AddDishActivity.this));
                                    frmIcon.setBackground(AppUtil.setCircleBackground(color, AddDishActivity.this));
                                    selectedColor = color;
                                    mDish.setColorBackground(Converter.covertColorToHexString(color));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).build();
            dialog.show(getSupportFragmentManager(), getString(R.string.fragment_picker));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích method: Hiển thị và thêm giá trị trả về của máy tính
     */
    public void setPrice(long price, String priceShow) {
        mDish.setDishPrice(price);
        etPrice.setText(priceShow);
    }

    /**
     * Mục đích Methob:
     *
     * @created_by tdcong
     * @date 5/31/2019
     * @param: unitList: danh sách đơn vị
     */
    @Override
    public void displayUnit(List<Unit> unitList) {
        try {
            unitSelected = unitList.get(ConstantKey.FIRST_UNIT);
            if (unitSelected == ConstantKey.UNIT_NO_SELECT) {
                tvUnit.setHint(getString(R.string.select_unit));
            } else {
                tvUnit.setText(unitSelected.getUnitName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích Methob: Lưu món thành công rồi load lại list ở màn hình chính
     *
     * @created_by tdcong
     * @date 6/3/2019
     */
    @Override
    public void saveNewDishSuccess() {
        try {
            Intent intentBroadCast = new Intent(ConstantKey.ACTION_NOTIFY_DATA);
            sendBroadcast(intentBroadCast);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveNewDishFail(String error) {

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

    public Unit getUnitSelected() {
        return unitSelected;
    }
}
