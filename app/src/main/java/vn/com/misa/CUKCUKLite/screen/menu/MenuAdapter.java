package vn.com.misa.CUKCUKLite.screen.menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
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
import vn.com.misa.CUKCUKLite.model.Dish;
import vn.com.misa.CUKCUKLite.util.AppUtil;

/**
 * Lớp adapter của danh sách món ăn
 * @Create_by: trand
 * @Date: 5/28/2019
 */
public class MenuAdapter extends BaseAdapter {

    private Context mContext;
    private List<Dish> mData;

    public MenuAdapter(Context mContext, List<Dish> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    /**
     * Hàm lấy ra size
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return: int
     */
    @Override
    public int getCount() {
        return mData.size();
    }

    /**
     * Hàm lấy ra đối tượng món ăn tại từng vị trí
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: position
     * @Return:
     */
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    /**
     * hàm lấy ra vị trí
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: position
     * @Return: position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Hàm lấy ra view
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param: position, convertView, parent
     * @Return: View
     */
    @SuppressLint("ResourceType")
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
            Dish dish = mData.get(position);
            viewHolder.tvFoodName.setText(dish.getDishName());
            viewHolder.tvPrice.setText(String.valueOf(dish.getDishPrice()));
            String iconName = dish.getDishIcon();
            String backgroundCode = dish.getColorBackground();
            try {
                if (!iconName.equals("")) {
                    //Xử lý khi có ảnh
                    InputStream ims = mContext.getAssets().open("icondefault/" + dish.getDishIcon() + ".png");
                    Drawable d = Drawable.createFromStream(ims, null);
                    viewHolder.ivFood.setImageDrawable(d);
                    ims.close();
                }else {
                    //Xử lý khi không có ảnh sẽ lấy ảnh mặc định
                    InputStream ims = mContext.getAssets().open("icondefault/ic_default.png");
                    Drawable d = Drawable.createFromStream(ims, null);
                    viewHolder.ivFood.setImageDrawable(d);
                    ims.close();
                }
                if(!backgroundCode.equals("")){
                    viewHolder.frmBackgroundColor.setBackground(AppUtil.createCircleBackground(backgroundCode));
                }else {
                    viewHolder.frmBackgroundColor.setBackground(AppUtil.createCircleBackground(mContext.getString(R.color.color_primary)));
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
     * Lớp ViewHolder
     * @Create_by: trand
     * @Date: 5/27/2019
     */
    private class ViewHolder {
        TextView tvFoodName, tvPrice;
        ImageView ivFood;
        FrameLayout frmBackgroundColor;

        public ViewHolder(View view) {
            try {
                tvFoodName = view.findViewById(R.id.tvdishName);
                tvPrice = view.findViewById(R.id.etPrice);
                ivFood = view.findViewById(R.id.ivDish);
                frmBackgroundColor = view.findViewById(R.id.frmBackgroundColor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
