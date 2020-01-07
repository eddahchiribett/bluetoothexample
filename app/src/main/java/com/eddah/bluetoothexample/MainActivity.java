package com.eddah.bluetoothexample;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_ENABLE_BT = 0;
    private final static int REQUEST_DISCOVER_BT = 1;
    private TextView mstatusBlueTv, mDiscoverable;
     private ImageView mBlueTv;
     private Button mOnBtn, mOffBtn,mDiscoverBtn;
     BluetoothAdapter mbluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mstatusBlueTv = findViewById(R.id.statusBluetoothTv);
        mDiscoverable = findViewById(R.id.discoverableDevicesBtn);
        mBlueTv = findViewById(R.id.bluetoothTv);
        mOnBtn =findViewById(R.id.onBtn);
        mOffBtn = findViewById(R.id.offBtn);
        mDiscoverBtn = findViewById(R.id.discoverableBtn);

        mbluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mbluetoothAdapter == null){
            mstatusBlueTv.setText("Bluetooth is not available");
        }else{
            mstatusBlueTv.setText("Bluetooth is available");
        }
        if (mbluetoothAdapter.isEnabled()){
            mBlueTv.setImageResource(R.drawable.ic_action_on);
        }else {
            mBlueTv.setImageResource(R.drawable.ic_action_off);
        }
        mOnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mbluetoothAdapter.isEnabled()){
                    showToast("Turning Bluetooth On ...");
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent,REQUEST_ENABLE_BT);
                }else {
                    showToast("Bluetooth already on");
                }

            }
        });
        mDiscoverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (!mbluetoothAdapter.isDiscovering()){
                    showToast("Making Your Device Discoverable");
                    Intent intent= new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent,REQUEST_DISCOVER_BT);
               }
            }
        });
        mOffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mbluetoothAdapter.isEnabled()){
                    mbluetoothAdapter.disable();
                    showToast("Bluetooth Turning Off");
                    mBlueTv.setImageResource(R.drawable.ic_action_off);
                }
                else{
                    showToast("Bluetooth is already off");
                }

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case REQUEST_ENABLE_BT:
                if (resultCode == RESULT_OK) {
                    mBlueTv.setImageResource(R.drawable.ic_action_on);
                    showToast("Bluetooth is on");

            }else{
                  showToast("could'nt turn on Bluetooth");
            }
                break;

        }
    }
    private  void showToast (String msg){
        Toast.makeText(this, "msg", Toast.LENGTH_SHORT).show();
    }
}
