package com.example.formsimu;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class formfill extends Activity {
	 
	private static final  int REQUEST_CONNECT_DEVICE=1;
	private BluetoothAdapter mBluetoothAdapter=null;
	private BluetoothChatService mChatService=null;
	
	private static final int REQUEST_ENABLE_BT=2;
	protected static final int MESSAGE_STATE_CHANGE = 1;
	protected static final int MESSAGE_READ = 2;
	protected static final int MESSAGE_WRITE = 3;
	protected static final int MESSAGE_DEVICE_NAME = 4;
	protected static final int MESSAGE_TOAST = 5;
	protected static final String DEVICE_NAME = "device_name";
	protected static final String TOAST = "toast";
	 
     String arr1[],arr2[],str1,str2;
     String str3;
	 int len1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
	
		 
		
	         // str3.equals(message1);
	   
		 
		if(mBluetoothAdapter==null)
		{
			Toast.makeText(this, "Bluetooth is not avilable", Toast.LENGTH_LONG).show();
			finish();
			return;
		}
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if(!mBluetoothAdapter.isEnabled())
		{
			Intent enableIntent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableIntent,REQUEST_ENABLE_BT);
		}
		else
		{
			if(mChatService==null)
			{
				
				setupchat();
			}
		}
	}

@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(mChatService!=null)
		{
			if(mChatService.getState()==BluetoothChatService.STATE_NONE)
			{
			mChatService.start();	
			}
		}
	}
	private void ensureDiscoverable() {
		// TODO Auto-generated method stub
		if(mBluetoothAdapter.getScanMode()!=BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE)
		{
			Intent intentdiscoverable=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			intentdiscoverable.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
			startActivity(intentdiscoverable);
		}
	}
	public void setupchat()
	{
		//final EditText mOutEditText1=(EditText)findViewById(R.id.editText1);
		
		//final EditText mOutEditText2=(EditText)findViewById(R.id.editText2);
		arr1=getResources().getStringArray(R.array.list1);
		ArrayAdapter<String>adp1=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,arr1);
		AutoCompleteTextView autxt1= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		autxt1.setThreshold(1);
		autxt1.setAdapter(adp1);
		
		arr2=getResources().getStringArray(R.array.list2);
		ArrayAdapter<String>adp2=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arr2);
		AutoCompleteTextView autxt2= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView2);
		autxt2.setThreshold(1);
		autxt2.setAdapter(adp2);
		Button mSendbutton=(Button)findViewById(R.id.button1);
		mSendbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "in first function", Toast.LENGTH_SHORT).show();
					
				  EditText mOutEditText1=(EditText)findViewById(R.id.editText1);
				 
				 String message1=mOutEditText1.getText().toString();
	                 // str3.equals(message1);
				 message1=message1.concat(";");
			
				
	          
	       
	           
	             
	        //   Toast.makeText(getApplicationContext(), "length :" + len1 ,Toast.LENGTH_SHORT ).show();
				
				
				
					
				//Toast.makeText(getApplicationContext(), "in second function", Toast.LENGTH_SHORT).show();
					 EditText mOutEditText2=(EditText)findViewById(R.id.editText2);
				String message2=mOutEditText2.getText().toString();
				//str3.equals(str3.concat(message2));
				// final int  len2=message2.length();
				 
				message1=message1.concat(message2+";");
				     
		   
			            
				
            
					
					//Toast.makeText(getApplicationContext(), "in third function", Toast.LENGTH_SHORT).show();
					 EditText mOutEditText3=(EditText)findViewById(R.id.editText3);
				String message3=mOutEditText3.getText().toString();
				//str3.equals(str3.concat(message3));
				// len3=message3.length();
				   message1=message1.concat(message3+";");
		     
	               
				
                
	              //  Toast.makeText(getApplicationContext(), "in fourth function", Toast.LENGTH_SHORT).show();
	                 EditText mOutEditText4=(EditText)findViewById(R.id.editText4);
                     String message4=mOutEditText4.getText().toString();
                    // str3.equals(str3.concat(message4));
                     //len4=message4.length();
              	   message1=message1.concat(message4+";");
	             
	          
         
                	
	               // Toast.makeText(getApplicationContext(), "in fifth function", Toast.LENGTH_SHORT).show();
	                 EditText mOutEditText5=(EditText)findViewById(R.id.editText5);
                     String message5=mOutEditText5.getText().toString();
                    // str3.equals(str3.concat(message5));
                     //len5=message5.length();
              	   message1=message1.concat(message5+";");
            
                	//Toast.makeText(getApplicationContext(), "in sixth function", Toast.LENGTH_SHORT).show();
                	AutoCompleteTextView txtv1=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
                	String str1=txtv1.getText().toString();
             	   message1=message1.concat(str1+";");
                	 //str3.equals(str3.concat(str1));
                	 //len6=str1.length();
                	
                	//Toast.makeText(getApplicationContext(), "in seventh function", Toast.LENGTH_SHORT).show();
                	AutoCompleteTextView txtv2=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView2);
                	String str2=txtv2.getText().toString();
             	   message1=message1.concat(str2+";");
                	//str3.equals(str3.concat(str2));
                	//len7=str2.length();*/
                	sendMessage(message1);
			}  	
			});
		mChatService=new BluetoothChatService(this,mHandler);
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(mChatService!=null)
		{
			mChatService.stop();
		}
	}
	
	
	private void sendMessage(String message)
	{
		
		if(mChatService.getState()!=BluetoothChatService.STATE_CONNECTED)
		{
			Toast.makeText(this, "not connected", Toast.LENGTH_LONG).show();
			return;
		}
		if(message.length()>0)
		{
			byte[]send=message.getBytes();
			mChatService.write(send);
			
		}
		
	}
	
	
	 
	
	public Handler mHandler=new Handler()
	{
		public Object mConnectedDeviceName;

		@Override
		public void handleMessage(Message msg)
		{			
			switch(msg.what)
			{
			case MESSAGE_STATE_CHANGE:
				switch(msg.arg1)
				{
				case BluetoothChatService.STATE_CONNECTED:
					break;
				case BluetoothChatService.STATE_CONNECTING:
					break;
				case BluetoothChatService.STATE_LISTEN:
					break;
				case BluetoothChatService.STATE_NONE:
					break;
				}
				break;
			case MESSAGE_WRITE:
			//	byte[]writeBuf=(byte[])msg.obj;
			//	String writeMessage=new String (writeBuf);
				/*if(i==1){
					 view1.setText(writeMessage);
					 
					 break;	
				       }
				          
			if(i==2){
				
			  
				   view2.setText(writeMessage);
				   break;	
				  
				}*/
			break;
				//mConversationArraayAdapter.add("Me: "+ writeMessage);
				
			case MESSAGE_READ:
				
			
              byte[]readBuf=(byte[])msg.obj;
            
				String readMessage=new String (readBuf);
					   
						//Toast.makeText(getApplicationContext(),"length :" + len1 , Toast.LENGTH_SHORT ).show();
						//String strr1=readMessage.substring(0, len1);
					
				String[] token;
				
					token=readMessage.split(";");
				
					TextView view1=(TextView)findViewById(R.id.editText1);
						view1.setText(token[0]);
					//	Toast.makeText(getApplicationContext(), " in first " + token[0] ,Toast.LENGTH_SHORT ).show();
						
							
					
						//String strr2=readMessage.substring( 2,3);
						TextView view2=(TextView)findViewById(R.id.editText2);
						view2.setText(token[1]);
					//	Toast.makeText(getApplicationContext(), "in second " + token[1] ,Toast.LENGTH_SHORT ).show();
						//view2.setText(strr2);
						
			

						// break;	
					
					
				
						TextView view3=(TextView)findViewById(R.id.editText3);
						view3.setText(token[2]);
					//	Toast.makeText(getApplicationContext(), "in third " + token[2] ,Toast.LENGTH_SHORT ).show();
						
						
						

						
					
				
						TextView view4=(TextView)findViewById(R.id.editText4);
						view4.setText(token[3]);
						//Toast.makeText(getApplicationContext(), "in forth " + token[3] ,Toast.LENGTH_SHORT ).show();
						
						
						
				
						TextView view5=(TextView)findViewById(R.id.editText5);
						view5.setText(token[4]);
					//	Toast.makeText(getApplicationContext(), "in  fifth " + token[4] ,Toast.LENGTH_SHORT ).show();
					
					
						TextView view6=(TextView)findViewById(R.id.autoCompleteTextView1);
						view6.setText(token[5]);
					//	Toast.makeText(getApplicationContext(), "in sixth " + token[5] ,Toast.LENGTH_SHORT ).show();
						
						
						

						TextView view7=(TextView)findViewById(R.id.autoCompleteTextView2);
						view7.setText(token[6]);
					//	Toast.makeText(getApplicationContext(), "in seventh " + token[6] ,Toast.LENGTH_SHORT ).show();
						
						
					

					
					
				
				
				
					break;
				
				
				//mConversationArraayAdapter.add(mConnectedDeviceName + ": "+ readMessage);
				
			 case MESSAGE_DEVICE_NAME:
	             // save the connected device's name
	             mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
	             Toast.makeText(getApplicationContext(), "Connected to "
	                            + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
	             break;
	         case MESSAGE_TOAST:
	             Toast.makeText(getApplicationContext(), msg.getData().getString(TOAST),
	                            Toast.LENGTH_SHORT).show();
	             break;
				
			}
		}
	};
	public void onActivityResult(int requestCode,int resultCode, Intent data)
	{
		 switch (requestCode) {
		    case REQUEST_CONNECT_DEVICE:
		        // When DeviceListActivity returns with a device to connect
		        if (resultCode == Activity.RESULT_OK) {
		            // Get the device MAC address
		            String address = data.getExtras()
		                                 .getString(DeviceList.EXTRA_DEVICE_ADDRESS);
		            // Get the BLuetoothDevice object
		            BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
		            // Attempt to connect to the device
		            mChatService.connect(device);
		        }
		        break;
		    case REQUEST_ENABLE_BT:
		        // When the request to enable Bluetooth returns
		        if (resultCode == Activity.RESULT_OK) {
		            // Bluetooth is now enabled, so set up a chat session
		            setupchat();
		        } else {
		            // User did not enable Bluetooth or an error occured
		           
		            Toast.makeText(this,"bt_not_enabled_leaving", Toast.LENGTH_SHORT).show();
		            finish();
		        }
		    }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		case R.id.scan:
			Intent server=new Intent(this,DeviceList.class);
			startActivityForResult(server, REQUEST_CONNECT_DEVICE);
			return true;
		case R.id.discoverable:
			ensureDiscoverable();
			return true;
		}
		return false;
	}

	
	

}
