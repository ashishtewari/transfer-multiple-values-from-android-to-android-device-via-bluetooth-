package com.example.formsimu;

import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DeviceList extends Activity{

	public static final String EXTRA_DEVICE_ADDRESS="device_address";
	private ArrayAdapter<String> mpairedDevicesArrayAdapter;
	private ArrayAdapter<String> mNewDevicesArrayAdapter;
	private BluetoothAdapter mBtAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deviceslist);
		Button btnscan=(Button)findViewById(R.id.button_scan);
		btnscan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doDiscoverey();
				v.setVisibility(View.GONE);
			}
		});
		mpairedDevicesArrayAdapter=new ArrayAdapter<String>(this, R.layout.devicename);
		mNewDevicesArrayAdapter=new ArrayAdapter<String>(this, R.layout.devicename);
		ListView mpairedlistview=(ListView)findViewById(R.id.listView1);
		mpairedlistview.setAdapter(mpairedDevicesArrayAdapter);
		mpairedlistview.setOnItemClickListener(mDeviceClickListener);
		ListView mnewlistview=(ListView)findViewById(R.id.listView2);
		mnewlistview.setAdapter(mNewDevicesArrayAdapter);
		mnewlistview.setOnItemClickListener(mDeviceClickListener);
		
		IntentFilter filter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
		this.registerReceiver(mReceiver, filter);
		filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mReceiver, filter);
        mBtAdapter=BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice>PairedDevices=mBtAdapter.getBondedDevices();
        
        if(PairedDevices.size()>0)
        {
        	findViewById(R.id.textView1).setVisibility(View.VISIBLE);
        	for (BluetoothDevice device : PairedDevices) {
                mpairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        } else {
            String noDevices = getResources().getText(R.string.none_paired).toString();
            mpairedDevicesArrayAdapter.add(noDevices);
        }
       
        }
	
		private void doDiscoverey()
		{
			setProgressBarIndeterminateVisibility(true);
			setTitle("scanning");
			findViewById(R.id.textView2).setVisibility(View.VISIBLE);
			if(mBtAdapter.isDiscovering())
			{
				mBtAdapter.cancelDiscovery();
			}
			mBtAdapter.startDiscovery();
		}
		private OnItemClickListener mDeviceClickListener=new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,long arg3) {
				// TODO Auto-generated method stub
				mBtAdapter.cancelDiscovery();
				String info=((TextView)v).getText().toString();
				String address=info.substring(info.length()-17);
				Intent intent=new Intent();
				intent.putExtra(EXTRA_DEVICE_ADDRESS,address);
				setResult(Activity.RESULT_OK, intent);
				finish();
				
			}
		};
		private final BroadcastReceiver mReceiver=new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				String action=intent.getAction();
				if(BluetoothDevice.ACTION_FOUND.equals(action))
				{
					BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
					if(device.getBondState()!=BluetoothDevice.BOND_BONDED)
					{
						mNewDevicesArrayAdapter.add(device.getName()+ "\n" + device.getAddress());
					}
				}else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
					setProgressBarIndeterminate(false);
					setTitle("select device" );
					if(mNewDevicesArrayAdapter.getCount()==0){
						String nodevice=getResources().getText(R.string.none_found).toString();
						mNewDevicesArrayAdapter.add(nodevice);
					}
				}
				
			}
		};
}
	


