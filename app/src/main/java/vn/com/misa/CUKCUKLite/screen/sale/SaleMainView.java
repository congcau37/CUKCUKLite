package vn.com.misa.CUKCUKLite.screen.sale;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.misa.CUKCUKLite.R;

/**
 *
 * @Create_by: trand
 * @Date: 5/29/2019
 * @Param:
 * @Return:
 */
public class SaleMainView extends Fragment {


    public SaleMainView() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sale_view, container, false);
    }

}
