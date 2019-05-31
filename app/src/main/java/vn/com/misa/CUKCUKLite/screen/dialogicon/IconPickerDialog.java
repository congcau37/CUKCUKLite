package vn.com.misa.CUKCUKLite.screen.dialogicon;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.util.ConstantKey;

@SuppressLint("ValidFragment")
public class IconPickerDialog extends DialogFragment implements IconPickerAdapter.OnItemClick, View.OnClickListener {

    @BindView(R.id.rvIcon)
    RecyclerView rvIcon;
    @BindView(R.id.tvCancel)
    TextView tvCancel;
    Unbinder unbinder;

    private IconPickerAdapter adapter;
    private Activity mActivity;
    View view;

//    public IconPickerDialog(AddDishActivity addDishActivity) {
//        mActivity = addDishActivity;
//    }
//
//    public IconPickerDialog(EditDishActivity editDishActivity) {
//        mActivity = editDishActivity;
//    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_NoActionBar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_icon_picker_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(getActivity());
        try {
            String[] images = getContext().getAssets().list(ConstantKey.PACKAGE_ICON_DEFAULT);
            List<String> listImages = new ArrayList<String>(Arrays.asList(images));
            rvIcon = view.findViewById(R.id.rvIcon);
            adapter = new IconPickerAdapter(getContext(), listImages, this);
            rvIcon.setAdapter(adapter);
            rvIcon.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onClick(String icon) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tvCancel)
    public void onViewClicked() {
        dismiss();
    }
}
