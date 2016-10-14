package com.example.pruebacontelephonymanager;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.IccOpenLogicalChannelResponse;
import android.telephony.TelephonyManager;
import android.telephony.*;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;



public class MainActivity extends Activity {
	
	final String LOG_TAG = "TelephonyManager";
	
	TextView _textview = null;
	ScrollView _scrollview = null;
	
	private static final byte[] AID1 = new byte[] { (byte) 0xA0, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00 }; // A0 00 00 00 02 00 00
	private static final byte[] AID2 = new byte[] { (byte) 0xA0, 0x00, 0x00, 0x00, 0x03, 0x00, 0x00, 0x00 }; // A0 00 00 00 03 00 00 00
	private static final byte[] AID3 = new byte[] { (byte) 0xA0, 0x00, 0x00, 0x01, 0x51, 0x41, 0x43, 0x4C, 0x00 }; //A0 00 00 01 51 41 43 4C 00
	private static final byte[] AID4 = new byte[] { (byte) 0xA0, 0x00, 0x00, 0x00, 0x63, 0x50, 0x4B, 0x43, 0x53, 0x2D, 0x31, 0x35 };//A0 00 00 00 63 50 4B 43 53 2D 31 35
	
	final String AID1s = "A0000000020000";
	final String AID2s = "A000000003000000";
	final String AID3s = "A00000015141434C00";
	final String AID4s = "A000000063504B43532D3135";
	
	private static String bytesToString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bytes)
			sb.append(String.format("%02x ", b & 0xFF));
		
		return sb.toString();
	}

	private void logText(String message) {
		_scrollview.post(new Runnable() {
			public void run() {
				_scrollview.fullScroll(ScrollView.FOCUS_DOWN);
			}

		});
		_textview.append(message);
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		LinearLayout layout = new LinearLayout(this);
		layout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		_scrollview = new ScrollView(this);
		_textview = new TextView(this);
		_textview.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		_scrollview.addView(_textview);
		layout.addView(_scrollview);

		setContentView(layout);
		
		logText("Datos de la UICC" + "\n");
		
		logText("....................." + "\n");
		
		TelephonyManager  tm=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		
		 String IMEINumber=tm.getDeviceId();  
	        String subscriberID=tm.getDeviceId();  
	        String SIMSerialNumber=tm.getSimSerialNumber();  
	        String networkCountryISO=tm.getNetworkCountryIso();  
	        String SIMCountryISO=tm.getSimCountryIso();  
	        String softwareVersion=tm.getDeviceSoftwareVersion();  
	        String voiceMailNumber=tm.getVoiceMailNumber();
	        
	        logText("IMEINumber: " + IMEINumber+ "\n");
	        logText("subscriberID: "+subscriberID+ "\n");
	        logText("SIMSerialNumber: "+SIMSerialNumber+ "\n");
	        logText("networkCountryISO: "+networkCountryISO+ "\n");
	        logText("SIMCountryISO: " + SIMCountryISO+ "\n");
	        logText("softwareVersion"+softwareVersion+ "\n");
	        logText("voiceMailNumber"+voiceMailNumber+ "\n");
	              
	        boolean isRoaming=tm.isNetworkRoaming();
	        
	        logText("isRoaming: " +isRoaming + "\n");
	        
	
	        //prueba de envio de comandos AT
	        try{
	      byte[] repuesta = tm.iccExchangeSimIO(176,28589,0,0,3,"010001");
	      logText("repuesta " + repuesta + "\n");
	        }
	        catch (Exception e1){
	        	Log.e(LOG_TAG, "Error occured1:", e1);
	        	Log.e(LOG_TAG, "--------------------------------------------------");
	        	logText("Error occured1 " + e1 + "\n");
	        } 
	        
	        //Abrir canal
	        try{
	        IccOpenLogicalChannelResponse channel = tm.iccOpenLogicalChannel(AID2s);
	        }
	        catch (Exception e2){
	        	Log.e(LOG_TAG, ".............................................");
	        	logText("Error occured2 " + e2 + "\n");
	        	Log.e(LOG_TAG, "Error occured:", e2);
	        }
	       
	        logText("................................... "  + "\n");
	       
	      	  
		
	}
}
