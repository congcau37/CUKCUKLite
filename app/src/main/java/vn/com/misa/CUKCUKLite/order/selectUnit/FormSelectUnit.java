package vn.com.misa.CUKCUKLite.order.selectUnit;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.model.Unit;
import vn.com.misa.CUKCUKLite.order.selectUnit.adapter.UnitAdapter;
import vn.com.misa.CUKCUKLite.order.editFood.FormEditFood;
import vn.com.misa.CUKCUKLite.util.ConstantKey;

/**
 * @param
 * @created_by tdcong
 * @date 5/23/2019
 * @return
 */
public class FormSelectUnit extends AppCompatActivity implements IUnitContract.IUnitView {

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

    String newUnit;
    Unit unitSelected;
    Dialog dialogAddUnit;

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
            loadUnitSelected();
            lvUnit.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendUnitSelected() {
        unitSelected = adapter.getUnitCurrentSelected();
        Intent intent= getIntent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantKey.KEY_SEND_UNIT,unitSelected);
        intent.putExtra(ConstantKey.KEY_SEND_UNIT,bundle);
        setResult(1,intent);
    }

    /**
     * @Create_by: trand
     * @Date: 5/27/2019
     */
    private void loadUnitSelected() {
        try {
            Bundle bundle = getIntent().getBundleExtra(ConstantKey.KEY_SEND_UNIT);
            unitSelected = (Unit) bundle.getSerializable(ConstantKey.KEY_SEND_UNIT);
            adapter.setCurrentSelected(unitSelected.getUnitID());
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
    @Override
    public void saveNewUnitSuccess(Unit unit) {
        try {
            dialogAddUnit.dismiss();
            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Create_by: trand
     * @Date: 5/27/2019
     */
    @Override
    public void saveNewUnitFail() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @created_by tdcong
     * @date 5/23/2019
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
            TextView tvTitle = dialogAddUnit.findViewById(R.id.tvTitleDialog);
            final EditText etUnitName = dialogAddUnit.findViewById(R.id.etUnitName);
            ImageView btnTitleClose = dialogAddUnit.findViewById(R.id.btnTitleClose);
            Button btnSave = dialogAddUnit.findViewById(R.id.btnSave);

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
                        newUnit = etUnitName.getText().toString().trim();
                        iUnitPresenter.saveNewUnit(newUnit);
                        dialogAddUnit.dismiss();
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
     * @param
     * @return
     * @created_by tdcong
     * @date 5/24/2019
     */
    public void checkScreenUit() {

    }
}
