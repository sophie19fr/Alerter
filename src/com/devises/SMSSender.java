package com.devises;

import java.util.ArrayList;
import java.util.List;
import android.telephony.SmsManager;
import android.widget.EditText;



public class SMSSender {
	private SmsManager smsManager;
	
	/**
	 * 
	 * CONSTRUCTEUR
	 * 
	 * Initialise l'objet SmsManager 
	 * 
	 */
	public SMSSender(){
	    this.smsManager = SmsManager.getDefault();
	}
	
	/**
	 * 
	 * ENVOI DU SMS
	 * 
	 * @param numeros
	 * @param editTextMessage
	 */
	public void sendSMS(List<String> numeros, EditText editTextMessage){
//		ArrayList<String> parts = this.smsManager.divideMessage(editTextMessage.toString());
		
		// sendTextMessage(phonenumber, service center address, message, sentIntent, deliveryIntent)		 
		for (int i = 0; i < numeros.size(); i++) {
			this.smsManager.sendTextMessage(numeros.get(i), null, editTextMessage.getText().toString(), null, null);
		}
			 
	}

	
}
