package vn.com.misa.CUKCUKLite.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.model.Food;

/**
 * Class danh sách thực đơn
 * @created_by tdcong
 * @date 5/17/2019
 */
public class OrderView extends Fragment implements IOrderContract.IOrderView {

    Unbinder unbinder;
    @BindView(R.id.lvFood)
    ListView lvFood;
    View view;
    OrderAdapter adapter;
    IOrderContract.IOrderPresenter orderPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            view = inflater.inflate(R.layout.fragment_orderpresenter, container, false);
            unbinder = ButterKnife.bind(this, view);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            super.onViewCreated(view, savedInstanceState);
            initView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @created_by tdcong
     * @date 5/17/2019
     * @param
     * @return
     */
    private void initView() {
        try {
            orderPresenter = new OrderPresenter(new OrderModel(getContext()), OrderView.this);
            orderPresenter.loadAllFood();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /**
    * Hàm hiển thị danh sach thực đơn
    * @created_by tdcong
    * @date 5/17/2019
    * @param
    * @return
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

    @Override
    public void onDestroyView() {
        try {
            super.onDestroyView();
            unbinder.unbind();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
