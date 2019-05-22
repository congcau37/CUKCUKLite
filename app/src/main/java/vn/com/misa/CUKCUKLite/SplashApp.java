package vn.com.misa.CUKCUKLite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import vn.com.misa.CUKCUKLite.login.LoginApp;

public class SplashApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_app);
        new Timer().schedule(new TimerTask() {
                                 public void run() {
                                     SplashApp.this.startActivity(new Intent(SplashApp.this, LoginApp.class));
                                     SplashApp.this.finish();
                                 }
                             }
                , 3000L);
        return;
    }
}
