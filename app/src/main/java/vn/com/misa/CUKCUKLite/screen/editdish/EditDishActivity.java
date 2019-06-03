package vn.com.misa.CUKCUKLite.screen.editdish;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.thebluealliance.spectrum.SpectrumDialog;

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
import vn.com.misa.CUKCUKLite.screen.dialogicon.IconPickerDialog;
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
    Dialog dialogConfirmDelete;

    IChooseUnitContract.IPresenter iPresenterUnit;
    IEditDishContract.IPresenter iPresenterDish;
    Dish detailDish;
    Unit unitSelected;
    int foodID;
    String foodStatus = "";
    final int REQUEST_CODE = 0;
    final int RESULT_CODE = 1;
    private int selectedColor;
    private static final int COLOR_DEF = -14235942;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_edit_dish);
            ButterKnife.bind(this);
            initPresenter();
            initToolBar();
            // gọi hàm lấy ra chi tiết món
            getDetailDish();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm ánh xạ toolbar
     *
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
     * Hàm xử lý bắt sự kiên click view
     *
     * @created_by tdcong
     * @date 5/22/2019
     */
    @OnClick({R.id.ivBack, R.id.tvSaveDish, R.id.tvUnit, R.id.cbStatus, R.id.ivSelectUnit, R.id.btnSave, R.id.btnDelete, R.id.frmBackgroundColor, R.id.frmBackgroundIcon})
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
                    if (validateFormEditDish())
                        updateDish();
                    break;
                case R.id.btnDelete:
                    showConfirmDialog();
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
                case R.id.frmBackgroundColor:
                    try {
                        showDialogPickColor();
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
     * Hàm lấy ra chi tiết món ăn
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     */
    private void getDetailDish() {
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
            //láy ra đơn vị đã chọn dựa theo với id món hiện tại
            unitSelected = iPresenterUnit.getUnit(detailDish.getUnitID());
            //lấy các giá trị gán vào thuộc tính
            foodID = detailDish.getDishID();
            String dishName = detailDish.getDishName();
            String dishPrice = NumberFormat.getIntegerInstance(Locale.GERMAN).format(detailDish.getDishPrice());
            String icon = detailDish.getDishIcon();
            String foodBackgroundColor = detailDish.getColorBackground();
            String foodBackgroundIcon = detailDish.getColorBackground();
            boolean Status = Converter.convertStatusMenu(detailDish.getDishStatus());
            if (unitSelected == null) {
                unitSelected = ConstantKey.UNIT_NO_SELECT;
                tvUnit.setHint(getString(R.string.select_unit));
            } else {
                unitName = unitSelected.getUnitName();
                tvUnit.setText(unitName);
            }
            etDishName.setText(dishName);
            etPrice.setText(dishPrice);
            //đọc hình ảnh bằng thư viện glide
            Glide.with(this).load(iPresenterDish.getBitmapFromAssets(this, icon)).into(ivDish);
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
     */
    private void updateDish() {
        try {
            if (validateFormEditDish()) {
                String foodName = etDishName.getText().toString().trim();
                long foodPrice = Converter.convertToLong(etPrice.getText().toString().trim());
                Dish dish = new Dish();
                dish.setDishID(detailDish.getDishID());
                String backgroundColor = (detailDish.getColorBackground());
                String foodIcon = detailDish.getDishIcon();
                if (unitSelected == null) {
                    dish.setUnitID(ConstantKey.UNIT_NO_SELECT.getUnitID());
                } else {
                    dish.setUnitID(unitSelected.getUnitID());
                }
                dish.setDishName(foodName);
                dish.setDishPrice(foodPrice);
                dish.setColorBackground(backgroundColor);
                dish.setDishIcon(foodIcon);
                dish.setDishStatus(foodStatus);
                iPresenterDish.updateDish(dish);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    .setSelectedColor(selectedColor != 0 ? selectedColor : COLOR_DEF)
                    .setColors(R.array.arr_colors)
//                    .setFixedColumnCount(4)
                    .setOnColorSelectedListener(new SpectrumDialog.OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(boolean positiveResult, @ColorInt int color) {
                            if (positiveResult) {
                                try {
                                    frmBackgroundColor.setBackground(AppUtil.setCircleBackground(color, EditDishActivity.this));
                                    frmBackgroundIcon.setBackground(AppUtil.setCircleBackground(color, EditDishActivity.this));
                                    selectedColor = color;
                                    detailDish.setColorBackground(Converter.covertColorToHexString(color));
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
     * Mục đích Methob: Hiển thị dialog xác nhận xóa
     * @created_by tdcong
     * @date 6/3/2019
     */
    private void showConfirmDialog() {
        try {
            dialogConfirmDelete = new Dialog(this, R.style.Theme_Dialog);
            dialogConfirmDelete.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogConfirmDelete.setContentView(R.layout.dialog_confirm_delete);
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialogConfirmDelete.setCancelable(true);
            dialogConfirmDelete.setCanceledOnTouchOutside(true);
            Button btnYes =  dialogConfirmDelete.findViewById(R.id.btnYes);
            Button btnNo =  dialogConfirmDelete.findViewById(R.id.btnNo);
            TextView tvConfirm = dialogConfirmDelete.findViewById(R.id.tvConfirm);
            tvConfirm.setText(String.format(getString(R.string.confirm_remove_dish),detailDish.getDishName()));
            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        iPresenterDish.deleteDish(detailDish);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        dialogConfirmDelete.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            dialogConfirmDelete.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích Methob: kiểm tra form chỉnh sửa đơn vị khi người dùng nhập
     *
     * @created_by tdcong
     * @date 5/31/2019
     */
    private boolean validateFormEditDish() {
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
     * Mục đích Methob:
     *
     * @created_by tdcong
     * @date 6/3/2019
     */
    public void setIcon(String icon) {
        try {
            Glide.with(this).load(iPresenterDish.getBitmapFromAssets(this, icon)).into(ivDish);
            detailDish.setDishIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            Intent intent = new Intent(EditDishActivity.this, ChooseUnitActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(ConstantKey.KEY_SEND_UNIT, unitSelected);
            intent.putExtra(ConstantKey.KEY_SEND_UNIT, bundle);
            startActivityForResult(intent, REQUEST_CODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mục đích Methob: load lại dữ liệu màn hình thực đơn
     *
     * @created_by tdcong
     * @date 6/3/2019
     */
    private void loadBroadcast() {
        try {
            Intent intentBroadCast = new Intent(ConstantKey.ACTION_NOTIFY_DATA);
            sendBroadcast(intentBroadCast);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            loadBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDishFail() {

    }

    @Override
    public void deleteDishSuccess() {
        try {
            dialogConfirmDelete.dismiss();
            loadBroadcast();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDishFail() {

    }
}
