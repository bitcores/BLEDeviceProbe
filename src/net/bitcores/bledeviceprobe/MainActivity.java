package net.bitcores.bledeviceprobe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import net.bitcores.btadapter.BleAdapter;
import net.bitcores.btadapter.BtCommon;

public class MainActivity extends Activity {
	BleAdapter bleAdapter;
	Handler mmHandler;
	
	static int REQUEST_ENABLE_BT = 1121;
	static final String TAG = "bledeviceprobe";
		
	private View cServiceView = null;
	private Button pairList;
	private Button disconnectButton;
	private TextView connectedDeviceText;
	private ListView serviceListView;
	private ListView characteristicListView;
	private String connectedDevice = "";
	private Integer state = Constants.STATE_DISCONNECTED;
	
	private HashMap<String, List<String>> servicesCharacteristics = new HashMap<String, List<String>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		bleAdapter = new BleAdapter();
		mmHandler = new Handler();
		
		pairList = (Button)this.findViewById(R.id.pairList);
		disconnectButton = (Button)this.findViewById(R.id.disconnect);
		connectedDeviceText = (TextView)this.findViewById(R.id.connectedDeviceText);
		serviceListView = (ListView)this.findViewById(R.id.serviceListView);
		characteristicListView = (ListView)this.findViewById(R.id.characteristicListView);
		
		pairList.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				pairListDialog();
			}	
		});
		pairList.setEnabled(true);
		
		disconnectButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				bleAdapter.disconnectDevice(connectedDevice);
				disconnectButton.setEnabled(false);
			}		
		});
		disconnectButton.setEnabled(false);
		
		connectedDeviceText.setText(getResources().getString(R.string.connecteddevicedefault));
		
		if (bleAdapter.initBle(MainActivity.this, mHandler, false, null, null)) {
			Intent intent = new Intent(MainActivity.this, BleAdapter.class);
			startService(intent);
		
			if (!BtCommon.mBluetoothAdapter.isEnabled()) {
				Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
			} else {
				// TODO something?
			}	
		} else {
			//	TODO bluetooth not supported on device
		}

	}
	
	
	private void startConnect(String address) {
		connectedDevice = address;
		
		mmHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				BtCommon.mBluetoothAdapter.stopLeScan(mLeScanCallback);
				if (state == Constants.STATE_SCANNING) {
					pairList.setEnabled(true);
				}
			}
		}, 10000);
		
		pairList.setEnabled(false);
		state = Constants.STATE_SCANNING;
		BtCommon.mBluetoothAdapter.startLeScan(mLeScanCallback);
	}
	
	private void testDevice(BluetoothDevice device) {
		String address = device.getAddress();
		if (address.equals(connectedDevice)) {
			BtCommon.mBluetoothAdapter.stopLeScan(mLeScanCallback);
			state = Constants.STATE_CONNECTING;
			bleAdapter.connectDevice(address);
		}
	}
	
	private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {	
		@Override
		public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
			testDevice(device);
		}
	};
	
	
	public void sortList(List<String> tosort) {
		Collections.sort(tosort, new Comparator<String>()
	    {
	        public int compare(String o1, String o2) 
	        {
	            return String.CASE_INSENSITIVE_ORDER.compare(o1, o2);
	        }
	    });
	}
	
	private void createServiceList(String address) {
		final List<String> serviceList = bleAdapter.getServiceList(address);
		sortList(serviceList);
		List<String> serviceListNamed = new ArrayList<String>();
		for (String suuid : serviceList) {
			List<String> characteristicList = bleAdapter.getCharacteristicList(address, suuid);
			sortList(characteristicList);
			servicesCharacteristics.put(suuid, characteristicList);
			
			String key = "0x" + suuid.substring(4, 8).toUpperCase();
			if (Constants.serviceMap.containsKey(key)) {
				serviceListNamed.add(Constants.serviceMap.get(key));
			} else {
				serviceListNamed.add(suuid);
			}
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listitem, serviceListNamed);
		
		serviceListView.setAdapter(adapter);
		serviceListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
				createCharacteristicList(serviceList.get(position));
				if (cServiceView != null) {
					cServiceView.setBackgroundColor(0x00000000);				
				}
				cServiceView = view;
				cServiceView.setBackgroundColor(0x33333333);
			}
			
		});
		
		serviceListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {

				return false;
			}
			
		});
	}
	
	private void createCharacteristicList(String suuid) {
		final List<String> characteristicList = servicesCharacteristics.get(suuid);
		List<String> characteristicListNamed = new ArrayList<String>();
		
		for (String cuuid : characteristicList) {			
			String key = "0x" + cuuid.substring(4, 8).toUpperCase();
			if (Constants.characteristicMap.containsKey(key)) {
				characteristicListNamed.add(Constants.characteristicMap.get(key));
			} else {
				characteristicListNamed.add(cuuid);
			}
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listitem, characteristicListNamed);
		
		characteristicListView.setAdapter(adapter);
		
		characteristicListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {

				return false;
			}
			
		});
	}
	
	
	private void removeServiceList() {
		serviceListView.setAdapter(null);
	}
	
	private void removeCharacteristicList() {
		characteristicListView.setAdapter(null);
	}
	

	private void pairListDialog() {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		
		Set<BluetoothDevice> pairedDevices = BtCommon.mBluetoothAdapter.getBondedDevices();
			
		List<String> devices = new ArrayList<String>();
		if (pairedDevices.size() > 0) {
			for (BluetoothDevice device : pairedDevices) {
				if (device.getType() == BluetoothDevice.DEVICE_TYPE_LE || device.getType() == BluetoothDevice.DEVICE_TYPE_DUAL) {
					devices.add(device.getName() + "\n" + device.getAddress());
				}
			}
		}
		final String[] items = devices.toArray(new String[devices.size()]);

		dialogBuilder.setSingleChoiceItems(items, -1, null);
		dialogBuilder.setPositiveButton("Connect", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//connect to selectedItem
				int selectedItem = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
				//Log.i(TAG, "selected item " + selectedItem);
				if (selectedItem != AdapterView.INVALID_POSITION) {
					String[] selected = items[selectedItem].split("\n");
					startConnect(selected[1]);
				}
			}		
		}).setNegativeButton("Cancel", null);
		
		AlertDialog pairListDialog = dialogBuilder.create();
		
		pairListDialog.show();
	}
	
	public final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String name;
			String address;
			
			switch (msg.what) {
				case BtCommon.MESSAGE_CONNECTED_DEVICE:
					name = msg.getData().getString("DEVICE_NAME");
					address = msg.getData().getString("DEVICE_ADDRESS");;
					
					connectedDeviceText.setText(name + " - " + address);
					disconnectButton.setEnabled(true);
					state = Constants.STATE_CONNECTED;
					
					break;
				case BtCommon.MESSAGE_DISCONNECT_DEVICE:
					connectedDevice = "";
					connectedDeviceText.setText(getResources().getString(R.string.connecteddevicedefault));
					removeServiceList();
					removeCharacteristicList();
					pairList.setEnabled(true);
					disconnectButton.setEnabled(false);
					servicesCharacteristics.clear();
					state = Constants.STATE_DISCONNECTED;
					
					break;
				case BtCommon.MESSAGE_SERVICES_DISCOVERED:
					Log.i(TAG, "services discovered");
					address = msg.getData().getString("DEVICE_ADDRESS");
					createServiceList(address);
				    	
					break;
				default:
					break;
			}
		}
	};
}
