package com.wangzhen.admin.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.DeviceAdapter;
import cb.CallBackDevice;
import common.Consants;
import receiver.BlueToothReceiver;

import static android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_FINISHED;
import static android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_STARTED;
import static android.bluetooth.BluetoothDevice.ACTION_BOND_STATE_CHANGED;
import static android.bluetooth.BluetoothDevice.ACTION_FOUND;

public class MainActivity extends AppCompatActivity implements CallBackDevice {

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothServerSocket bluetoothServerSocket ;
    private List<BluetoothDevice> list_device;
    private BlueToothReceiver blueToothReceiver;
    private DeviceAdapter deviceAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        initView();
        initData();
        initReceiver();
    }

    //初始化各View对象
    private void initView() {
        recyclerView = findViewById(R.id.recycleView);
    }

    //初始化各数据对象
    private void initData() {
        list_device = new ArrayList<>();
        deviceAdapter = new DeviceAdapter(this,list_device);
        recyclerView.setAdapter(deviceAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     * 注册广播
     */
    private void initReceiver() {
        blueToothReceiver = new BlueToothReceiver(this,this);
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.bt_search:
                if(!bluetoothAdapter.isEnabled()){
                    boolean isEnable = bluetoothAdapter.enable();
                    if(isEnable){
                        Toast.makeText(this,"蓝牙开启成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this,"蓝牙开启失败",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                bluetoothAdapter.startDiscovery();
                break;
            case R.id.bt_connect:
                break;
            case R.id.bt_sendMsg:

                break;
            case R.id.canFound:
                canDiscovery();//能够被检测到
                break;
            default:
                break;
        }
    }

    //开启蓝牙可被检测
    private void canDiscovery() {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(intent);
    }

    /**
     * 广播回调blueDevice
     * @param bluetoothDevice
     */
    @Override
    public void callBackDevice(BluetoothDevice bluetoothDevice) {
        if(!list_device.contains(bluetoothDevice)){
            list_device.add(bluetoothDevice);
            deviceAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        blueToothReceiver.unRegiseterReceiver();
    }
}
