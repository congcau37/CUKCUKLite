package vn.com.misa.CUKCUKLite.screen.caculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.com.misa.CUKCUKLite.R;

public class CaculatorFragment extends Fragment {


    public CaculatorFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_caculator, container, false);
    }

}
