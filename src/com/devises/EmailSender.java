package com.devises;

import android.content.Intent;
import android.net.Uri;

public class EmailSender {
	Intent emailIntent;
	
	public EmailSender(){
		emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setData(Uri.parse("mailto:"));
		emailIntent.setType("text/plain");
	}
	
	public void sendEmail(String[] adress, String subject, String body){
		emailIntent.putExtra(Intent.EXTRA_EMAIL  , adress);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		emailIntent.putExtra(Intent.EXTRA_TEXT   , body);
	}
}
