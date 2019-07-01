package vn.com.misa.CUKCUKLite.screen.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.screen.adddish.AddDishActivity;
import vn.com.misa.CUKCUKLite.screen.menu.MenuActivity;
import vn.com.misa.CUKCUKLite.screen.sale.SaleActivity;

/**
 * Lớp màn hình chính của ứng dụng
 *
 * @created_by tdcong
 * @date 5/17/2019
 */
public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content_main)
    ConstraintLayout contentMain;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.tvTitleToolbar)
    TextView tvTitleToolbar;
    @BindView(R.id.ivAvatar)
    CircleImageView ivAvatar;
    @BindView(R.id.lnSale)
    LinearLayout lnSale;
    @BindView(R.id.lnMenu)
    LinearLayout lnMenu;
    @BindView(R.id.lnReport)
    LinearLayout lnReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
            ButterKnife.bind(this);
            initView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm
     *
     * @param
     * @return
     */
    private void initView() {
        try {
            setSupportActionBar(toolbar);
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            lnSale.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvTitleToolbar.setText(R.string.menu_sale);
                    initFragment(R.id.content_main, new SaleActivity());
                    DrawerLayout drawer = findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                }
            });

            lnMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvTitleToolbar.setText(R.string.menu_order);
                    initFragment(R.id.content_main, new MenuActivity());
                    DrawerLayout drawer = findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                }
            });
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            tvTitleToolbar.setText(R.string.menu_order);

            initFragment(R.id.content_main, new MenuActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm thay thế fragment
     *
     * @param id,fragment
     */
    private void initFragment(int id, Fragment fragment) {
        try {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(id, fragment);
            fragmentTransaction.commit();
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
    public void onBackPressed() {
        try {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);

            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            getMenuInflater().inflate(R.menu.main, menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * hàm xử lý sự kiên toolbar
     *
     * @param
     * @return
     * @created_by tdcong
     * @date 5/22/2019
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            int id = item.getItemId();
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_main);
            if (id == R.id.action_add) {
                if (currentFragment != null && currentFragment instanceof MenuActivity) {
                    startActivity(new Intent(HomeActivity.this, AddDishActivity.class));
                }
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onOptionsItemSelected(item);
    }
}
