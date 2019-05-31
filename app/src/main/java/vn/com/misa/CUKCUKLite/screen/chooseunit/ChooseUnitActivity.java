package vn.com.misa.CUKCUKLite.screen.chooseunit;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.model.Unit;
import vn.com.misa.CUKCUKLite.util.ConstantKey;
import vn.com.misa.CUKCUKLite.util.helper.UnitListener;

/**
 * Lớp chọn đơn vị
 * @param
 * @created_by tdcong
 * @date 5/23/2019
 * @return
 */
public class ChooseUnitActivity extends AppCompatActivity implements IChooseUnitContract.IView, UnitListener {

    ChooseUnitAdapter adapter;
    IChooseUnitContract.IPresenter iPresenter;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.ivAddUnit)
    ImageView ivAddUnit;
    @BindView(R.id.toolbar)
    LinearLayout toolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.lvUnit)
    ListView lvUnit;
    @BindView(R.id.btnDone)
    Button btnDone;

    final int RESULT_CODE = 1;
    Unit unitSelected;
    Dialog dialogAddUnit, dialogDeleteUnit, dialogEditUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_form_select_unit);
            ButterKnife.bind(this);
            initPresenter();
            initToolBar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm khởi tạp presenter
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    private void initPresenter() {
        try {
            iPresenter = new ChooseUnitPresenter(new ChooseUnitModel(this), this,this);
            iPresenter.loadAllUnit();
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
            tvTitleToolbar.setText(getString(R.string.unit));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm xử lý sự kiện view
     * @param
     * @return
     * @created_by tdcong
     * @date 5/23/2019
     */
    @OnClick({R.id.ivBack, R.id.ivAddUnit, R.id.btnDone})
    public void onViewClicked(View view) {
        try {
            switch (view.getId()) {
                case R.id.ivBack:
                    try {
                        unitSelected = adapter.getUnitCurrentSelected();
                        if(unitSelected == ConstantKey.UNIT_NO_SELECT){
                            sendUnitSelected(ConstantKey.UNIT_NO_SELECT);
                        }else {
                            sendUnitSelected(unitSelected);
                        }
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.ivAddUnit:
                    showDialogAddUnit();
                    break;
                case R.id.btnDone:
                    if(adapter.getUnitCurrentSelected() == null){
                        Toast.makeText(this, getString(R.string.not_select_unit), Toast.LENGTH_SHORT).show();
                    }else {
                        unitSelected = adapter.getUnitCurrentSelected();
                        sendUnitSelected(unitSelected);
                        finish();
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm hiển thị danh sách đơn vị
     * @param unitList
     * @return
     * @created_by tdcong
     * @date 5/23/2019
     */
    @Override
    public void displayUnit(List<Unit> unitList) {
        try {
            adapter = new ChooseUnitAdapter(this, unitList);
            adapter.setUnitListener((UnitListener) this);
            loadUnitSelected();
            lvUnit.setAdapter(adapter);
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
    private void sendUnitSelected(Unit unit) {
        try {
            Intent intent = getIntent();
            Bundle bundle = new Bundle();
            bundle.putSerializable(ConstantKey.KEY_SEND_UNIT, unit);
            intent.putExtra(ConstantKey.KEY_SEND_UNIT, bundle);
            setResult(RESULT_CODE, intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm lấy ra đơn vị đã chọn
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    private void loadUnitSelected() {
        try {
            Bundle bundle = getIntent().getBundleExtra(ConstantKey.KEY_SEND_UNIT);
            if(bundle!=null){
                unitSelected = (Unit) bundle.getSerializable(ConstantKey.KEY_SEND_UNIT);
                if(unitSelected == ConstantKey.UNIT_NO_SELECT){
                    adapter.setUnitCurrentSelected(null);
                }
                adapter.setUnitCurrentSelected(unitSelected);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm hiển thị dialog Thêm đơn vị
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    public void showDialogAddUnit() {
        try {
            dialogAddUnit = new Dialog(this, R.style.Theme_Dialog);
            dialogAddUnit.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogAddUnit.setContentView(R.layout.dialog_add_edit_unit);
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialogAddUnit.setCancelable(true);
            dialogAddUnit.setCanceledOnTouchOutside(true);

            Button btnCancel = dialogAddUnit.findViewById(R.id.btnCancel);
            Button btnSave = dialogAddUnit.findViewById(R.id.btnSave);
            TextView tvTitle = dialogAddUnit.findViewById(R.id.tvTitleDialog);
            final EditText etUnitName = dialogAddUnit.findViewById(R.id.etUnitName);
            ImageView btnTitleClose = dialogAddUnit.findViewById(R.id.btnTitleClose);
            tvTitle.setText(getString(R.string.add_unit));

            btnTitleClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        dialogAddUnit.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        dialogAddUnit.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String newUnit = etUnitName.getText().toString().trim();
                        iPresenter.saveNewUnit(newUnit);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            dialogAddUnit.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm hiển thị dialog sửa đơn vị
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @Override
    public void showDialogEditUnit(final Unit unit) {
        try {
            dialogEditUnit = new Dialog(this, R.style.Theme_Dialog);
            dialogEditUnit.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogEditUnit.setContentView(R.layout.dialog_add_edit_unit);
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialogEditUnit.setCancelable(true);
            dialogEditUnit.setCanceledOnTouchOutside(true);

            Button btnCancel = dialogEditUnit.findViewById(R.id.btnCancel);
            Button btnSave = dialogEditUnit.findViewById(R.id.btnSave);
            TextView tvTitle = dialogEditUnit.findViewById(R.id.tvTitleDialog);
            final EditText etUnitName = dialogEditUnit.findViewById(R.id.etUnitName);
            ImageView btnTitleClose = dialogEditUnit.findViewById(R.id.btnTitleClose);

            String uniName = unit.getUnitName();
            tvTitle.setText(getString(R.string.edit_unit));
            etUnitName.setText(uniName);
            btnTitleClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        dialogEditUnit.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        dialogEditUnit.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String newUnitName = etUnitName.getText().toString().trim();
                        unit.setUnitName(newUnitName);
                        iPresenter.updateUnit(unit);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            dialogEditUnit.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm hiển thị dialog xóa đơn vị
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @Override
    public void showDialogDeleteUnit(final Unit unit) {
        final int unitID = unit.getUnitID();
        try {
            dialogDeleteUnit = new Dialog(this, R.style.Theme_Dialog);
            dialogDeleteUnit.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogDeleteUnit.setContentView(R.layout.dialog_delete_unit);
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialogDeleteUnit.setCancelable(true);
            dialogDeleteUnit.setCanceledOnTouchOutside(true);

            Button btnNo = dialogDeleteUnit.findViewById(R.id.btnNo);
            Button btnYes = dialogDeleteUnit.findViewById(R.id.btnYes);
            ImageView btnTitleClose = dialogDeleteUnit.findViewById(R.id.btnTitleClose);
            final TextView tvConfirm = dialogDeleteUnit.findViewById(R.id.tvConfirm);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvConfirm.setText(Html.fromHtml(getString(R.string.you_can_delete_unit), Html.FROM_HTML_MODE_COMPACT));
            } else {
                tvConfirm.setText(Html.fromHtml(getString(R.string.you_can_delete_unit)));
            }

            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogDeleteUnit.dismiss();
                }
            });
            btnYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteUnit(unitID);
                    dialogDeleteUnit.dismiss();
                }
            });
            btnTitleClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        dialogDeleteUnit.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            dialogDeleteUnit.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm hiển thị khi thêm mới đơn vị thành công
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: newUnit
     * @Return:
     */
    @Override
    public void saveNewUnitSuccess(Unit newUnit) {
        try {
            iPresenter.loadAllUnit();
            adapter.setUnitCurrentSelected(newUnit);
            unitSelected = adapter.getUnitCurrentSelected();
            sendUnitSelected(unitSelected);
            dialogAddUnit.dismiss();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm hiển thị khi thêm mới đơn vị thất bại
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: error
     * @Return:
     */
    @Override
    public void saveNewUnitFail(String error) {
        try {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm hiển thị cập nhật đơn vị thành công
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @Override
    public void updateUnitSuccess(Unit unit) {
        try {
            iPresenter.loadAllUnit();
            adapter.setUnitCurrentSelected(unit);
            unitSelected = adapter.getUnitCurrentSelected();
            sendUnitSelected(unitSelected);
            dialogEditUnit.dismiss();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm hiển thị cập nhật đơn vị thất bại
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @Override
    public void updateUnitFail(String error) {
        try {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm xóa đơn vị
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: unitID
     * @Return:
     */
    @Override
    public void deleteUnit(int unitID) {
        iPresenter.deleteUnit(unitID);
    }

    /**
     * Hàm hiển thị xóa đơn vị thành công
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @Override
    public void deleteUnitSuccess(int unitID) {
        try {
            adapter.checkUnitCurrenSelect(unitID);
            adapter.remove(unitID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm hiển thị xóa đơn vị thất bại
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @Override
    public void deleteUnitFail() {
        try {
            Toast.makeText(this, getString(R.string.unit_used), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
