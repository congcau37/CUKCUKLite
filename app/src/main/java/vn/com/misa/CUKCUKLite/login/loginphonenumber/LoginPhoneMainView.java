package vn.com.misa.CUKCUKLite.login.loginphonenumber;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.com.misa.CUKCUKLite.MainApp;
import vn.com.misa.CUKCUKLite.R;

public class LoginPhoneMainView extends AppCompatActivity implements ILoginContract.ILoginView {

    @BindView(R.id.btnBack)
    ImageButton btnBack;
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.edUserName)
    EditText edUserName;
    @BindView(R.id.lnClearUserName)
    LinearLayout lnClearUserName;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.lnClearPassword)
    LinearLayout lnClearPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;
    @BindView(R.id.imgInfo)
    ImageView imgInfo;
    @BindView(R.id.layoutLogin)
    LinearLayout layoutLogin;

    String userName, passWord;
    LoginPresenter iLoginPresenter;
    String shfKey = "save login";// key để lưu thông tin đăng nhập
    @BindView(R.id.cbSaveLogin)
    CheckBox cbSaveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_phone_number);
            ButterKnife.bind(this);
            initPresenter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btnBack, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                break;
            case R.id.btnLogin:
                getTextAccount();
                iLoginPresenter.loadAccount(userName, passWord);
                break;
        }
    }

    /**
     * Hàm khởi tạo presenter
     */
    private void initPresenter() {
        iLoginPresenter = new LoginPresenter(new LoginModel(), this);
    }

    /**
     * Hàm lấy thông tin đăng nhập
     */
    public void getTextAccount() {
        try {
            userName = edUserName.getText().toString().trim();
            passWord = edPassword.getText().toString().trim();

            String pass= String.format(getString(R.string.et_forgot_password),passWord);
            Log.d("SBC", pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm
     *
     * @param
     * @return
     * @created_by tdcong
     * @date 5/17/2019
     */
    @Override
    public void showOnSuccess() {
        try {
            startActivity(new Intent(LoginPhoneMainView.this, MainApp.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param
     * @return
     * @created_by tdcong
     * @date 5/17/2019
     */
    @Override
    public void showOnFail() {
        try {
            Toast.makeText(this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * hàm lưu trạng thái
     *
     * @param
     * @return
     * @created_by tdcong
     * @date 5/17/2019
     */
    public void savingPreferences() {
        try {
            SharedPreferences pre = getSharedPreferences
                    (shfKey, MODE_PRIVATE);
            SharedPreferences.Editor editor = pre.edit();
            String user = edUserName.getText().toString();
            String pwd = edPassword.getText().toString();
            boolean bchk = cbSaveLogin.isChecked();
            if (!bchk) {
                editor.clear();
            } else {
                editor.putString("user", user);
                editor.putString("pwd", pwd);
                editor.putBoolean("checked", bchk);
            }
            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * hàm đọc trạng thái đã lưu trước đó
     */
    public void restoringPreferences() {
        try {
            SharedPreferences pre = getSharedPreferences
                    (shfKey, MODE_PRIVATE);
            boolean bchk = pre.getBoolean("checked", false);
            if (bchk) {
                String user = pre.getString("user", "");
                String pwd = pre.getString("pwd", "");
                edUserName.setText(user);
                edPassword.setText(pwd);
            }
            cbSaveLogin.setChecked(bchk);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        try {
            super.onResume();
            restoringPreferences();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        try {
            super.onPause();
            savingPreferences();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
