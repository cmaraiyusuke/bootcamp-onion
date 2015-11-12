package jp.classmethod.onion.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.classmethod.onion.R;
import jp.classmethod.onion.domain.onion.Onion;
import jp.classmethod.onion.domain.onion.TasteType;

/**
 * たまねぎ一覧のリストアダプター
 */
public class OnionListAdapter extends BaseAdapter {

    public static final int TYPE_ONION = 0;

    private final LayoutInflater mInflater;

    private List<Onion> mOnions = new ArrayList<>();

    public OnionListAdapter(LayoutInflater inflater) {
        this.mInflater = inflater;
    }

    public void setItems(Collection<? extends Onion> items) {
        this.mOnions = new ArrayList<>(items);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mOnions.size();
    }

    @Override
    public Object getItem(int position) {
        return mOnions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return TYPE_ONION;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.item_onion, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        Onion onion = (Onion) getItem(position);
        String farmText = "(" + onion.farm + ")";
        holder.name.setText(onion.name);
        holder.farm.setText(farmText);
        holder.taste.setImageResource(mappingToResourceId(onion.taste));

        return convertView;
    }

    private int mappingToResourceId(TasteType type) {
        switch (type) {
            case BITTER:
                return R.mipmap.bitter;
            case NORMAL:
                return R.mipmap.normal;
            case SWEET:
                return R.mipmap.sweet;
            default:
                throw new IllegalArgumentException("Unexpected TasteType: " + type);
        }
    }

    static class ViewHolder {
        @Bind(R.id.name) TextView name;
        @Bind(R.id.farm) TextView farm;
        @Bind(R.id.taste) ImageView taste;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
