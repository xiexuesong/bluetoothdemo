package adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangzhen.admin.bluetoothdemo.R;

import java.util.List;

/**
 * Created by admin on 2018/9/21.
 */

public class DeviceAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<BluetoothDevice> list_device;
    private LayoutInflater layoutInflater;

    public DeviceAdapter(Context context, List<BluetoothDevice> list_device) {
        this.context = context;
        this.list_device = list_device;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_recyclerview, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DeviceViewHolder deviceViewHolder = (DeviceViewHolder) holder;
        BluetoothDevice bluetoothDevice = list_device.get(position);
        deviceViewHolder.textView_address.setText(bluetoothDevice.getAddress());
        deviceViewHolder.textView_name.setText(bluetoothDevice.getName());
        String bondState ;
        if (bluetoothDevice.getBondState() == BluetoothDevice.BOND_NONE) {
            bondState = "没有配对";
        } else if (bluetoothDevice.getBondState() == BluetoothDevice.BOND_BONDING) {
            bondState = "正在配对";
        } else {
            bondState = "已配对";
        }
        deviceViewHolder.textView_state.setText(bondState);
    }

    @Override
    public int getItemCount() {
        return list_device.size();
    }

    class DeviceViewHolder extends RecyclerView.ViewHolder {

        TextView textView_name;
        TextView textView_state;
        TextView textView_address;

        public DeviceViewHolder(View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.textView_name);
            textView_address = itemView.findViewById(R.id.textView_address);
            textView_state = itemView.findViewById(R.id.textView_state);
        }
    }

}
