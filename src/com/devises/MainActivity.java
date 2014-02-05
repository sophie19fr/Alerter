package com.devises;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import com.example.alerter.R;

import android.os.Bundle;
import android.R.xml;
import android.app.Activity;
import android.content.Intent;
import android.util.Xml;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.telephony.SmsManager;

public class MainActivity extends Activity implements OnClickListener {

	// variables d'instances : éléments de l'activity

	private TextView textViewInfo;
	private EditText editTextSujet;
	private EditText editTextMessage;
	private RadioButton radioSms;
	private RadioButton radioEmail;
	private RadioButton radioBoth;
	private Button boutonEnvoyer;
	private List<String> resultTel = new ArrayList<String>();
	private List<String> resultMail = new ArrayList<String>();
	private StringBuilder strBuild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// valorisation des variables d'instance via les éléments de l'activity
		instancier();

		// definition des listeners
		setListeners();
		// récupération de la liste d'amis
		getFriendList();

		strBuild = new StringBuilder("");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// a chaque écoute, on active et/ou désactive les champs
		if (!v.toString().contains("Send")) {
			fieldEnabler(v);
		}
		else{
			messageModeChoser(v);
		}

	}

	public void instancier() {
		// valorisation des variables d'instance via les éléments de l'activity
		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
		editTextSujet = (EditText) findViewById(R.id.editTextSubject);
		editTextMessage = (EditText) findViewById(R.id.editTextMessage);
		radioSms = (RadioButton) findViewById(R.id.radio0);
		radioEmail = (RadioButton) findViewById(R.id.radio1);
		radioBoth = (RadioButton) findViewById(R.id.radio2);
		boutonEnvoyer = (Button) findViewById(R.id.buttonSend);

		// par défaut le sujet est désactivé (pour les SMS)
		editTextSujet.setEnabled(false);
		// on met donc le focus sur le message
		editTextMessage.requestFocus();
	}

	public void setListeners() {
		radioSms.setOnClickListener(this);
		radioEmail.setOnClickListener(this);
		radioBoth.setOnClickListener(this);
		boutonEnvoyer.setOnClickListener(this);
	}

	public void fieldEnabler(View v) {
		if (v.toString().contains("radio0")) {
			editTextSujet.setEnabled(false);
			editTextMessage.requestFocus();
		} else if (v.toString().contains("radio1")) {
			editTextSujet.setEnabled(true);
			editTextSujet.requestFocus();
		} else if (v.toString().contains("radio2")) {
			editTextSujet.setEnabled(true);
			editTextSujet.requestFocus();
		} else {
			textViewInfo.setText(v.toString());

		}
	}

	public void sendEmail(String[] adress) {
		String subject = editTextSujet.getText().toString();
		String body = editTextMessage.getText().toString();

		EmailSender emailSender = new EmailSender();
		emailSender.sendEmail(adress, subject, body);
		startActivity(Intent.createChooser(emailSender.getEmailIntent(),
				"envoi mail..."));
	}

	public void getFriendList() {
		// récupération des valeurs du XML
		XmlPullParser xpp = getResources().getXml(R.xml.amis);
		XmlParser xmlPars = new XmlParser(xpp);

		// l'attribut définit la valeur des champs que l'on veut fetcher
		// on récupère un tableau
		resultTel = xmlPars.getNodFromAttribute("telephone");
		resultMail = xmlPars.getNodFromAttribute("email");
	}
	
	public void messageModeChoser(View v){
		
		if(radioSms.isChecked()){
			//envoyer SMS
			SMSSender sms = new SMSSender();
			sms.sendSMS(resultTel, editTextMessage);
	        Toast.makeText(this, "Votre message à bien été envoyé", Toast.LENGTH_LONG).show();
		}
		else if(radioEmail.isChecked()){
			//transformer la liste de résultats XML en tableau de String
			String[] toSend = (String[]) resultMail.toArray();
			sendEmail(toSend);
			textViewInfo.setText("Ca veut envoyer un mail");
		}
		else{
			//envoyer mail + SMS
		}
	}

	
	

}
