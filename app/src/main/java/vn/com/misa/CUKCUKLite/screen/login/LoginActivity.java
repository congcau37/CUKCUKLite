package vn.com.misa.CUKCUKLite.screen.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.base.BaseActivity;
import vn.com.misa.CUKCUKLite.screen.home.HomeActivity;
import vn.com.misa.CUKCUKLite.screen.login.logingoogle.LoginServices;
import vn.com.misa.CUKCUKLite.screen.login.loginphone.LoginPhoneActivity;

import static vn.com.misa.CUKCUKLite.util.ConstantKey.RC_SIGN_IN;

/**
 * Lớp đăng nhập chính
 *
 * @created_by tdcong
 * @date 5/17/2019
 */
public class LoginActivity extends BaseActivity{

    @BindView(R.id.llLogo)
    LinearLayout llLogo;
    @BindView(R.id.btnLoginPhoneEmail)
    Button btnLoginPhoneEmail;
    @BindView(R.id.tvNoAccount)
    TextView tvNoAccount;
    @BindView(R.id.btnLoginFacebook)
    Button btnLoginFacebook;
    @BindView(R.id.btnLoginGoogle)
    Button btnLoginGoogle;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private LoginServices loginServices;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_app);
            ButterKnife.bind(this);


            //yêu cầu người dùng cung cấp các thông tin cơ bản
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm xử lý các sự kiên
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    @OnClick({R.id.llLogo, R.id.btnLoginPhoneEmail, R.id.tvNoAccount, R.id.btnLoginFacebook, R.id.btnLoginGoogle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLoginFacebook:

                break;
            case R.id.btnLoginGoogle:
                signIn();
                break;
            case R.id.btnLoginPhoneEmail:
                startActivity(new Intent(LoginActivity.this, LoginPhoneActivity.class));
                break;
            case R.id.tvNoAccount:
                break;
        }
    }

    /**
     * Mục đính của methob:
     *
     * @param
     * @return
     * @Create_by: trand
     * @Date: 6/16/2019
     */
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            startActivity(new Intent(this, HomeActivity.class));
        } catch (ApiException e) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }
    }

    private void signOut(){
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {

            }
        });
    }
}
