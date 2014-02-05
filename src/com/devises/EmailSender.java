package com.devises;

import android.content.Intent;
import android.net.Uri;
import android.sax.StartElementListener;

public class EmailSender {
	Intent emailIntent;
	
	/**
	 * Constructor : Prépare un mail, de type texte
	 */
	public EmailSender(){
		emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setData(Uri.parse("mailto:"));
		emailIntent.setType("text/plain");
	}
	
	
	/**
	 * Méthode mettant en place l'envoi
	 * @param adress : tableau de Strings d'adresses email
	 * @param subject : String du sujet
	 * @param body : String du corps du mesage
	 */
	public void sendEmail(String[] adress, String subject, String body){
		emailIntent.putExtra(Intent.EXTRA_EMAIL  , adress);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
		emailIntent.putExtra(Intent.EXTRA_TEXT   , body);
		
	}

	public Intent getEmailIntent() {
		return emailIntent;
	}

	public void setEmailIntent(Intent emailIntent) {
		this.emailIntent = emailIntent;
	}
	
	
}
