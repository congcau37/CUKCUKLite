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
import android.view.Window;
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
import vn.com.misa.CUKCUKLite.screen.adddish.AddDishActivity;
import vn.com.misa.CUKCUKLite.screen.editdish.EditDishActivity;
import vn.com.misa.CUKCUKLite.util.ConstantKey;

@SuppressLint("ValidFragment")
public class IconPickerDialog extends DialogFragment implements IconPickerAdapter.OnItemClick, View.OnClickListener {

    @BindView(R.id.rvIcon)
    RecyclerView rvIcon;
    @BindView(R.id.tvCancel)
    TextView tvCancel;
    Unbinder unbinder;

    Activity mActivity;

    private IconPickerAdapter adapter;
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public IconPickerDialog(AddDishActivity addDishActivity) {
        mActivity = addDishActivity;
    }

    public IconPickerDialog(EditDishActivity editDishActivity) {
        mActivity = editDishActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_icon_picker_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(getActivity());
        try {
            String[] images = getContext().getAssets().list(ConstantKey.PACKAGE_ICON_DEFAULT);
            List<String> listImages = new ArrayList<String>(Arrays.asList(images));
            adapter = new IconPickerAdapter(getContext(), listImages, this);
            rvIcon.setAdapter(adapter);
            rvIcon.setLayoutManager(new GridLayoutManager(getActivity(), ConstantKey.VALUE_SPAN_COUNT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCancel:
                dismiss();
                break;
        }
    }

    @Override
    public void onClick(String icon) {
        try {
            if (mActivity instanceof AddDishActivity) {
                ((AddDishActivity) mActivity).setIcon(icon);
            } else if (mActivity instanceof EditDishActivity) {
                ((EditDishActivity) mActivity).setIcon(icon);
            }
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
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
