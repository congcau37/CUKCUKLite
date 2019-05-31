package vn.com.misa.CUKCUKLite.screen.chooseunit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import vn.com.misa.CUKCUKLite.R;
import vn.com.misa.CUKCUKLite.model.Unit;
import vn.com.misa.CUKCUKLite.util.ConstantKey;
import vn.com.misa.CUKCUKLite.util.helper.UnitListener;

/**
 * Lớp adapter đơn vị
 *
 * @created_by tdcong
 * @date 5/23/2019
 */
public class ChooseUnitAdapter extends BaseAdapter {
    private Context mContext;
    private List<Unit> mData;

    private UnitListener unitListener;
    private Unit unitCurrentSelected = null;

    public ChooseUnitAdapter(Context mContext, List<Unit> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    /**
     * Hàm gán listener
     *
     * @Create_by: trand
     * @Date: 5/29/2019
     * @Param: UnitListener
     * @Return:
     */
    public void setUnitListener(UnitListener unitListener) {
        this.unitListener = unitListener;
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
            final ChooseUnitAdapter.ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_unit, parent, false);
                viewHolder = new ChooseUnitAdapter.ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ChooseUnitAdapter.ViewHolder) convertView.getTag();
            }
            final Unit unit = mData.get(position);
            viewHolder.tvUnitName.setText(unit.getUnitName());
            if (unitCurrentSelected != ConstantKey.UNIT_NO_SELECT) {
                if (unit.getUnitID() == unitCurrentSelected.getUnitID()) {
                    viewHolder.ivChecked.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.ivChecked.setVisibility(View.INVISIBLE);
                }
            } else {
                viewHolder.ivChecked.setVisibility(View.INVISIBLE);
            }
            viewHolder.lnRowUnit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        unitCurrentSelected = unit;
                        notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            viewHolder.lnRowUnit.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (unitListener != null) {
                        unitListener.showDialogDeleteUnit(unit);
                    }
                    return false;
                }
            });

            viewHolder.rlEditUnit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (unitListener != null) {
                        unitListener.showDialogEditUnit(unit);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    /**
     * Lớp viewholder
     *
     * @Create_by: trand
     * @Date: 5/27/2019
     * @Param:
     * @Return:
     */
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

    /**
     * Hàm lấy ra đơn vị đã chọn
     */
    public Unit getUnitCurrentSelected() {
        return unitCurrentSelected;
    }

    /**
     * Hàm gán đơn vị đã chọn
     */
    public void setUnitCurrentSelected(Unit unitCurrentSelected) {
        this.unitCurrentSelected = unitCurrentSelected;
    }

    public void checkUnitCurrenSelect(int id) {
        if (id == unitCurrentSelected.getUnitID()) {
            unitCurrentSelected = ConstantKey.UNIT_NO_SELECT;
        }
    }

    public void remove(int unitID) {
        for (int i = mData.size() - 1; i >= 0; i--) {
            if (mData.get(i).getUnitID() == unitID) {
                mData.remove(i);
            }
        }
        notifyDataSetChanged();
    }

    public void showPopUpDeleteUnit(View view, final Unit unit) {
        try {
            PopupMenu popupMenu = new PopupMenu(mContext, view);
            popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    try {

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            });
//            setForceShowIcon(popupMenu);
            popupMenu.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
