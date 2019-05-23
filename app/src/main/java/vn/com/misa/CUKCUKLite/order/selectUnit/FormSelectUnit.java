package vn.com.misa.CUKCUKLite.order.selectUnit;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
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

/**
 * 
 * @created_by tdcong
 * @date 5/23/2019
 * @param 
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
            initView();
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
    private void initView() {
        iUnitPresenter = new UnitPresenter(this, new UnitModel(this));
        iUnitPresenter.loadUnit();
    }

    /**
     * @param
     * @return
     * @created_by tdcong
     * @date 5/23/2019
     */
    @OnClick({R.id.ivBack, R.id.ivAddUnit, R.id.btnDone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.ivAddUnit:
                showDialogAddUnit();
                break;
            case R.id.btnDone:
                finish();
                break;
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
            lvUnit.setAdapter(adapter);
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
    public void showDialogAddUnit() {
        try {
            final Dialog dialog = new Dialog(this,R.style.Theme_Dialog);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_add_edit_unit);
            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);

            Button dialogButton =  dialog.findViewById(R.id.btnCancel);
            TextView tvTitle = dialog.findViewById(R.id.tvTitleDialog);
            ImageView btnTitleClose = dialog.findViewById(R.id.btnTitleClose);

            btnTitleClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
