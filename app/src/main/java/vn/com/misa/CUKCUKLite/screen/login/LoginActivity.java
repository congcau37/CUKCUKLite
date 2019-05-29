package vn.com.misa.CUKCUKLite.screen.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.screen.login.loginphone.LoginPhoneActivity;

/**
 * Lớp đăng nhập chính
 * @created_by tdcong
 * @date 5/17/2019
 */
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.llLogo)
    LinearLayout llLogo;
    @BindView(R.id.btnLoginPhoneEmail)
    Button btnLoginPhoneEmail;
    @BindView(R.id.tvNoAccount)
    TextView tvNoAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_app);
            ButterKnife.bind(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm xử lý các sự kiên
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @OnClick({R.id.llLogo, R.id.btnLoginPhoneEmail, R.id.tvNoAccount})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llLogo:
                break;
            case R.id.btnLoginPhoneEmail:
                startActivity(new Intent(LoginActivity.this, LoginPhoneActivity.class));
                break;
            case R.id.tvNoAccount:
                break;
        }
    }
}
