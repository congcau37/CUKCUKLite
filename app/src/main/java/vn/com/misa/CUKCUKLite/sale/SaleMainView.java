package vn.com.misa.CUKCUKLite.sale;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.misa.CUKCUKLite.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleMainView extends Fragment {


    public SaleMainView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sale_view, container, false);
    }

}
