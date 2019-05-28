package vn.com.misa.CUKCUKLite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import vn.com.misa.CUKCUKLite.login.LoginMain;

/**
 * Lớp màn hình chờ
 * @Create_by: trand
 * @Date: 5/29/2019
 */
public class SplashApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_app);
            new Timer().schedule(new TimerTask() {
                                     public void run() {
                                         SplashApp.this.startActivity(new Intent(SplashApp.this, LoginMain.class));
                                         SplashApp.this.finish();
                                     }
                                 }
                    , 3000L);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
