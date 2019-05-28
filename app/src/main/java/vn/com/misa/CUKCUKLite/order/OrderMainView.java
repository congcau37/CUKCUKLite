package vn.com.misa.CUKCUKLite.order;

import android.content.Intent;
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
import vn.com.misa.CUKCUKLite.model.Food;
import vn.com.misa.CUKCUKLite.order.adapter.OrderAdapter;
import vn.com.misa.CUKCUKLite.order.editFood.FormEditFood;
import vn.com.misa.CUKCUKLite.util.ConstantKey;

/**
 * Class danh sách thực đơn
 *
 * @created_by tdcong
 * @date 5/17/2019
 */
public class OrderMainView extends Fragment implements IOrderContract.IOrderView {

    Unbinder unbinder;
    @BindView(R.id.lvFood)
    ListView lvFood;
    View view;
    OrderAdapter adapter;
    IOrderContract.IOrderPresenter iOrderPresenter;


    /**
     *
     * @created_by tdcong
     * @date 5/23/2019
     * @param
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            view = inflater.inflate(R.layout.fragment_order, container, false);
            unbinder = ButterKnife.bind(this, view);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    /**
     *
     * @created_by tdcong
     * @date 5/23/2019
     * @param
     * @return
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
     * @Create_by: trand
     * @Date: 5/28/2019
     * @Param:
     * @Return:
     */
    private void initPresenter() {
        try {
            iOrderPresenter = new OrderPresenter(new OrderModel(getContext()), OrderMainView.this);
            iOrderPresenter.loadAllFood();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm xử lý sự kiện view
     * @created_by tdcong
     * @date 5/22/2019
     * @param
     * @return
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
     * Hàm gửi chị tiết món ăn đã chọn từ danh sách
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: position
     * @Return:
     */
    private void sendDetailFood(int position) {
        Food food = (Food) adapter.getItem(position);
        Intent intent = new Intent(getContext(), FormEditFood.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstantKey.KEY_SEND_FOOD,food);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * Hàm hiển thị danh sach món ăn trong thực đơn
     * @param arrayList
     * @return
     * @created_by tdcong
     * @date 5/17/2019
     */
    @Override
    public void displayListOrder(List<Food> arrayList) {
        try {
            adapter = new OrderAdapter(getActivity(), arrayList);
            lvFood.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
