package vn.com.misa.CUKCUKLite.screen.menu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.model.Dish;
import vn.com.misa.CUKCUKLite.screen.editdish.EditDishActivity;
import vn.com.misa.CUKCUKLite.util.ConstantKey;

/**
 * Class danh sách thực đơn
 *
 * @created_by tdcong
 * @date 5/17/2019
 */
public class MenuActivity extends Fragment implements IMenuContract.IView {

    Unbinder unbinder;
    @BindView(R.id.lvFood)
    ListView lvFood;
    View view;
    MenuAdapter adapter;
    IMenuContract.IPresenter iMenuPresenter;
    BroadcastReceiver myBroadCast;


    /**
     * @param
     * @return
     * @created_by tdcong
     * @date 5/23/2019
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            view = inflater.inflate(R.layout.fragment_order, container, false);
            unbinder = ButterKnife.bind(this, view);
            initBroadCast();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    /**
     * @param
     * @return
     * @created_by tdcong
     * @date 5/23/2019
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            super.onViewCreated(view, savedInstanceState);
            initPresenter();
            initEvent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm khởi tạp presenter
     *
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    private void initPresenter() {
        try {
            iMenuPresenter = new MenuPresenter(new MenuModelModel(getContext()), MenuActivity.this,getContext());
            iMenuPresenter.loadAllFood();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm xử lý sự kiện view
     *
     * @param
     * @return
     * @created_by tdcong
     * @date 5/22/2019
     */
    private void initEvent() {
        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendDetailFood(position);
            }
        });
    }

    /**
     * Mục đích Methob: Khởi tạo broadcast và đăng ký
     * @created_by tdcong
     * @date 5/30/2019
     * @param: 
     * @return: 
     */
    private void initBroadCast() {
        try {
         myBroadCast = new BroadcastReceiver() {
             @Override
             public void onReceive(Context context, Intent intent) {
                 if(intent.getAction().equals(ConstantKey.ACTION_NOTIFY_DATA)){
                     iMenuPresenter.loadAllFood();
                     adapter.notifyDataSetChanged();
                 }
             }
         };

         final IntentFilter filter = new IntentFilter(ConstantKey.ACTION_NOTIFY_DATA);
         getActivity().registerReceiver(myBroadCast,filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm gửi chị tiết món ăn đã chọn từ danh sách
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: position
     * @Return:
     */
    private void sendDetailFood(int position) {
        Dish dish = (Dish) adapter.getItem(position);
        Intent intent = new Intent(getContext(), EditDishActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantKey.KEY_SEND_DISH, dish);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Hàm hiển thị danh sach món ăn trong thực đơn
     *
     * @param arrayList
     * @return
     * @created_by tdcong
     * @date 5/17/2019
     */
    @Override
    public void displayListOrder(List<Dish> arrayList) {
        try {
            adapter = new MenuAdapter(getActivity(), arrayList);
            lvFood.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
