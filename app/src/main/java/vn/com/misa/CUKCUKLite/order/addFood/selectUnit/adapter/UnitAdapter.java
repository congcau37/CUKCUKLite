package vn.com.misa.CUKCUKLite.order.addFood.selectUnit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.model.Unit;

/**
 * @param
 * @created_by tdcong
 * @date 5/23/2019
 * @return
 */
public class UnitAdapter extends BaseAdapter {
    private Context mContext;
    private List<Unit> mData;
    private int currentSelected = 1;

    public UnitAdapter(Context mContext, List<Unit> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        try {
            final UnitAdapter.ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_unit, parent, false);
                viewHolder = new UnitAdapter.ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (UnitAdapter.ViewHolder) convertView.getTag();
            }
            final Unit unit = mData.get(position);
            viewHolder.tvUnitName.setText(unit.getUnitName());
            if (unit.getUnitID() == currentSelected) {
                viewHolder.ivChecked.setVisibility(View.VISIBLE);
            } else {
                viewHolder.ivChecked.setVisibility(View.INVISIBLE);
            }
            viewHolder.lnRowUnit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentSelected = unit.getUnitID();
                    notifyDataSetChanged();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    private class ViewHolder {
        TextView tvUnitName;
        ImageView ivChecked;
        LinearLayout lnRowUnit;
        RelativeLayout rlEditUnit;

        public ViewHolder(View view) {
            try {
                tvUnitName = view.findViewById(R.id.tvUnitName);
                ivChecked = view.findViewById(R.id.ivChecked);
                lnRowUnit = view.findViewById(R.id.lnRowUnit);
                rlEditUnit = view.findViewById(R.id.rlEdit);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
