package com.laughing.android.bluetoothlamp;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * 作者：Laughing on 2017/7/24 16:49
 * 邮箱：719240226@qq.com
 */

public class DeviceAdapter extends BaseAdapter {
    private ArrayList<BluetoothDevice> mDevices;
    private Context mContext;

    public DeviceAdapter(Context context, ArrayList<BluetoothDevice> devices) {
        mDevices = devices;
        mContext = context;
    }

    @Override
    public int getCount() {
//        return 500;
        return mDevices.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item, null);
        }
        ViewHolder holder = ViewHolder.getHolder(convertView);
        Random random = new Random();
        int a = 220 + random.nextInt(35);
        int r = 10 + random.nextInt(180);
        int g = 10 + random.nextInt(180);
        int b = 10 + random.nextInt(180);
        //                view.setTextColor(Color.rgb(r, g, b));
        holder.mTvName.setTextColor(Color.argb(a, r, g, b));
        holder.mTvAddress.setTextColor(Color.argb(a, r, g, b));
        holder.mTvName.setText(mDevices.get(position).getName());
        holder.mTvAddress.setText(mDevices.get(position).getAddress());
//        holder.mTvName.setText("mDevices.get(position).getName()");
//        holder.mTvAddress.setText("mDevices.get(position).getAddress()");

        return convertView;
    }

    static class ViewHolder {
        TextView mTvName;
        TextView mTvAddress;

        public ViewHolder(View convertView) {
            mTvName = (TextView) convertView.findViewById(R.id.tv_name);
            mTvAddress = (TextView) convertView.findViewById(R.id.tv_address);
        }

        public static ViewHolder getHolder(View convertView) {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            if (holder == null) {
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);

            }
            return holder;
        }
    }
}
