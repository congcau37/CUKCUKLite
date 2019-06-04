package vn.com.misa.CUKCUKLite.screen.caculator;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.screen.caculator.model.InputKeys;
import vn.com.misa.CUKCUKLite.screen.caculator.model.Operators;
import vn.com.misa.CUKCUKLite.util.AppUtil;
import vn.com.misa.CUKCUKLite.util.ConstantKey;
import vn.com.misa.CUKCUKLite.util.helper.Converter;

import static vn.com.misa.CUKCUKLite.util.ConstantKey.DATA;
import static vn.com.misa.CUKCUKLite.util.ConstantKey.ID;
import static vn.com.misa.CUKCUKLite.util.ConstantKey.JSON_ASSETS;
import static vn.com.misa.CUKCUKLite.util.ConstantKey.NAME;
import static vn.com.misa.CUKCUKLite.util.ConstantKey.NUMBER_COLUMN;
import static vn.com.misa.CUKCUKLite.util.ConstantKey.TEXT_INPUT;
import static vn.com.misa.CUKCUKLite.util.helper.Converter.getNumberInput;

public class CalculatorFragment extends DialogFragment {

    private static IOnClickDone mIOnClickDone;
    RecyclerView rcvKeyboard;
    CalculatorAdapter mCalculatorAdapter;
    EditText etInputNumber;
    ImageView ivButtonCloseKeyboard;

    ArrayList<InputKeys> mDatasets;
    private String textInput = "";

    public CalculatorFragment() {

    }

    public static CalculatorFragment createInstance(String textInput, IOnClickDone mIOnClickDone) {
        CalculatorFragment calculatorFragment = new CalculatorFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TEXT_INPUT, textInput);
        calculatorFragment.setArguments(bundle);
        calculatorFragment.setOnClickDone(mIOnClickDone);
        return calculatorFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_caculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        rcvKeyboard = view.findViewById(R.id.rcvKeyboard);
        etInputNumber = view.findViewById(R.id.etInputNumber);
        ivButtonCloseKeyboard = view.findViewById(R.id.ivButtonCloseKeyboard);
        //khởi tạo bàn phím
        createItems();
        // khởi tạo adapter với list bàn phím đã khởi tạo
        mCalculatorAdapter = new CalculatorAdapter(mDatasets);
        //gọi sự kiên clink item tra về callback
        mCalculatorAdapter.setOnClickListener(new CalculatorAdapter.OnclickInputKey() {
            @Override
            public void onClickItem(int id) {
                try {
                    //click xong
                    if (id == 20) {
                        String inputText = etInputNumber.getText().toString();
                        long price = getNumberInput(etInputNumber.getText().toString());
                        mIOnClickDone.onClickDone(price, inputText);
                        dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            // Lấy ra dữ liệu đã gửi
            Bundle bundle = getArguments();
            if (bundle != null) {
                if (bundle.containsKey(TEXT_INPUT)) {
                    textInput = bundle.getString(TEXT_INPUT);
                    if (textInput != null && textInput.isEmpty()) {
                        etInputNumber.setSelection(etInputNumber.getText().length());
                        etInputNumber.selectAll();
                        return;
                    }
                    etInputNumber.setText(textInput);
                    etInputNumber.setSelection(etInputNumber.getText().length());
                    etInputNumber.selectAll();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Hiển thị list bán phím
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), NUMBER_COLUMN);
        rcvKeyboard.setLayoutManager(gridLayoutManager);
        rcvKeyboard.setAdapter(mCalculatorAdapter);

        //tắt máy tính
        ivButtonCloseKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        //ẩn bàn phím ảo
        etInputNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    AppUtil.hideKeyboard(getActivity());
                }
            }
        });
        etInputNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.hideKeyboard(getActivity());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hàm khởi tạo danh sách bàn phím
     *
     * @Create_by: trand
     * @Date: 6/4/2019
     */
    private void createItems() {
        mDatasets = new ArrayList<>();
        String jsonString = AppUtil.loadJSONFromAsset(getContext(), JSON_ASSETS);
        if (jsonString != null) {
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray datas = jsonObject.getJSONArray(DATA);
                for (int i = 0; i < datas.length(); i++) {
                    JSONObject o = (JSONObject) datas.get(i);
                    mDatasets.add(new InputKeys(o.getInt(ID), o.getString(NAME)));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            for (int i = 0; i < 10; i++) {
                mDatasets.add(new InputKeys((i + 1), String.valueOf(i)));
            }
        }
    }

    /**
     * Hàm gán Interface
     * @Create_by: trand
     * @Date: 6/4/2019
     */
    public void setOnClickDone(IOnClickDone mIOnClickDone) {
        CalculatorFragment.mIOnClickDone = mIOnClickDone;
    }

    /**
     * Interface cho sự kiện callback
     */
    public interface IOnClickDone {
        void onClickDone(long price, String priceShow);
    }

}
