package vn.com.misa.CUKCUKLite.order.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.model.Food;
import vn.com.misa.CUKCUKLite.util.AppUtil;

/**
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

    /**
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    @Override
    public int getCount() {
        return mData.size();
    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_order, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvFoodName.setText(mData.get(position).getFoodName());
            viewHolder.tvPrice.setText(String.valueOf(mData.get(position).getFoodPrice()));
            String iconName = mData.get(position).getFoodIcon();
            String backgroundCode = mData.get(position).getColorBackground();
            try {
                if (!iconName.equals("")) {
                    InputStream ims = mContext.getAssets().open("icondefault/" + mData.get(position).getFoodIcon() + ".png"); //mData.get(position).getFoodIcon()
                    Drawable d = Drawable.createFromStream(ims, null);
                    viewHolder.ivFood.setImageDrawable(d);
                    ims.close();
                }
                if(!backgroundCode.equals("")){
                    viewHolder.frmBackgroundColor.setBackground(AppUtil.createCircleBackground(backgroundCode));
                }

            } catch (IOException ex) {
                ex.getStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    /**
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
    private class ViewHolder {
        TextView tvFoodName, tvPrice;
        ImageView ivFood;
        FrameLayout frmBackgroundColor;

        public ViewHolder(View view) {
            try {
                tvFoodName = view.findViewById(R.id.tvFoodName);
                tvPrice = view.findViewById(R.id.etPrice);
                ivFood = view.findViewById(R.id.ivFood);
                frmBackgroundColor = view.findViewById(R.id.frmBackgroundColor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
