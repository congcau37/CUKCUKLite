package vn.com.misa.CUKCUKLite.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.model.Food;

/**
 *
 * @created_by tdcong
 * @date 5/17/2019
 */
public class OrderAdapter extends BaseAdapter {
    
    private Context mContext;
    private List<Food> mData;

    public OrderAdapter(Context mContext, List<Food> mData) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_order, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvFoodName.setText(mData.get(position).getFoodName());
        viewHolder.tvPrice.setText(String.valueOf(mData.get(position).getPrice()));
        return convertView;
    }

    private class ViewHolder {
        TextView tvFoodName,tvPrice;

        public ViewHolder(View view) {
            tvFoodName = view.findViewById(R.id.tvFoodName);
            tvPrice = view.findViewById(R.id.tvPrice);
        }
    }
}
