package vn.com.misa.CUKCUKLite.order.selectUnit;

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
import android.widget.AdapterView;
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
import vn.com.misa.CUKCUKLite.order.selectUnit.adapter.UnitAdapter;
import vn.com.misa.CUKCUKLite.order.editFood.FormEditFood;
import vn.com.misa.CUKCUKLite.util.ConstantKey;
import vn.com.misa.CUKCUKLite.util.helper.UnitListener;

/**
 * @param
 * @created_by tdcong
 * @date 5/23/2019
 * @return
 */
public class FormSelectUnit extends AppCompatActivity implements IUnitContract.IUnitView,UnitListener{

    UnitAdapter adapter;
    IUnitContract.IUnitPresenter iUnitPresenter;
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

    Unit unitSelected;
    Dialog dialogAddUnit,dialogDeleteUnit,dialogEditUnit;

    /**
     * @param
     * @return
     * @created_by tdcong
     * @date 5/23/2019
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_form_select_unit);
            ButterKnife.bind(this);
            initPresenter();
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
            iUnitPresenter.loadAllUnit();
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
    @OnClick({R.id.ivBack, R.id.ivAddUnit, R.id.btnDone})
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
                case R.id.ivAddUnit:
                    showDialogAddUnit();
                    break;
                case R.id.btnDone:
                    sendUnitSelected();
                    finish();
                    break;
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
    @Override
    public void displayUnit(List<Unit> unitList) {
        try {
            adapter = new UnitAdapter(this, unitList);
            adapter.setUnitListener((UnitListener) this);
            loadUnitSelected();
            lvUnit.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    private void sendUnitSelected() {
        try {
            unitSelected = adapter.getUnitCurrentSelected();
            Intent intent = getIntent();
            Bundle bundle = new Bundle();
            bundle.putSerializable(ConstantKey.KEY_SEND_UNIT, unitSelected);
            intent.putExtra(ConstantKey.KEY_SEND_UNIT, bundle);
            setResult(1, intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: 
     * @Return: 
     */
    private void loadUnitSelected() {
        try {
            Bundle bundle = getIntent().getBundleExtra(ConstantKey.KEY_SEND_UNIT);
            unitSelected = (Unit) bundle.getSerializable(ConstantKey.KEY_SEND_UNIT);
            adapter.setUnitCurrentSelected(unitSelected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /**
    *
    * @Create_by: trand
    * @Date: 5/28/2019
    * @Param: 
    * @Return: 
    */
    @Override
    public void saveNewUnitSuccess(Unit newUnit) {
        try {
            iUnitPresenter.loadAllUnit();
            adapter.setUnitCurrentSelected(newUnit);
            sendUnitSelected();
            dialogAddUnit.dismiss();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param: 
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
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @Override
    public void deleteUnit(int unitID) {
        iUnitPresenter.deleteUnit(unitID);
    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @Override
    public void updateUnitSuccess() {
        try {
            dialogEditUnit.dismiss();
            iUnitPresenter.loadAllUnit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @Override
    public void updateUnitFail() {

    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @Override
    public void deleteUnitSuccess() {
        try {
            iUnitPresenter.loadAllUnit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @Override
    public void deleteUnitFail() {
        try {
            Toast.makeText(this, "Đơn vị đã được sử dụng. Bạn không được phép xóa", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
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
            tvTitle.setText(getString(R.string.tv_title_add_unit));

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
                        iUnitPresenter.saveNewUnit(newUnit);
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
     *
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
            tvTitle.setText(getString(R.string.tv_title_edit_unit));
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
                        dialogEditUnit.dismiss();
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
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @Override
    public void showDialogDeleteUnit(final Unit unit){
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
            TextView tvConfirm = dialogDeleteUnit.findViewById(R.id.tvConfirm);

            final int unitID = unit.getUnitID();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvConfirm.setText(Html.fromHtml(getString(R.string.tv_dialog_delete_unit), Html.FROM_HTML_MODE_COMPACT));
            } else {
                tvConfirm.setText(Html.fromHtml(getString(R.string.tv_dialog_delete_unit)));
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
     * @param
     * @return
     * @created_by tdcong
     * @date 5/24/2019
     */
    public void checkScreenUit() {

    }
}
