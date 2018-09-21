package receiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import cb.CallBackDevice;

import static android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_FINISHED;
import static android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_STARTED;
import static android.bluetooth.BluetoothDevice.ACTION_BOND_STATE_CHANGED;
import static android.bluetooth.BluetoothDevice.ACTION_FOUND;

/**
 * Created by admin on 2018/9/18.
 */

public class BlueToothReceiver extends BroadcastReceiver {

    private CallBackDevice callBackDevice;
    private Context context;

    public BlueToothReceiver(Context context, CallBackDevice callBackDevice) {
        this.context = context;
        this.callBackDevice = callBackDevice;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_FOUND);
        intentFilter.addAction(ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(ACTION_DISCOVERY_FINISHED);
        intentFilter.addAction(ACTION_BOND_STATE_CHANGED);
        context.registerReceiver(this, intentFilter);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action) {
            case ACTION_FOUND:
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                callBackDevice.callBackDevice(device);
                break;
            case ACTION_DISCOVERY_STARTED:
                break;
            case ACTION_DISCOVERY_FINISHED:
                break;
            case ACTION_BOND_STATE_CHANGED:
                break;
            default:
                break;
        }
    }

    //取消广播注册
    public void unRegiseterReceiver() {
        this.context.unregisterReceiver(this);
    }
}
