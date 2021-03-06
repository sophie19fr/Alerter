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

public class MainActivity extends Activity implements OnClickListener {

	// variables d'instances : �l�ments de l'activity

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

		// valorisation des variables d'instance via les �l�ments de l'activity
		instancier();

		// definition des listeners
		setListeners();
		// r�cup�ration de la liste d'amis
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
		// a chaque �coute, on active et/ou d�sactive les champs
		if (!v.toString().contains("Send")) {
			fieldEnabler(v);
		} else {
			messageModeChoser();
		}

	}

	public void instancier() {
		// valorisation des variables d'instance via les �l�ments de l'activity
		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
		editTextSujet = (EditText) findViewById(R.id.editTextSubject);
		editTextMessage = (EditText) findViewById(R.id.editTextMessage);
		radioSms = (RadioButton) findViewById(R.id.radio0);
		radioEmail = (RadioButton) findViewById(R.id.radio1);
		radioBoth = (RadioButton) findViewById(R.id.radio2);
		boutonEnvoyer = (Button) findViewById(R.id.buttonSend);

		// par d�faut le sujet est d�sactiv� (pour les SMS)
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
		// r�cup�ration des valeurs du XML
		XmlPullParser xpp = getResources().getXml(R.xml.amis);
		XmlParser xmlPars = new XmlParser(xpp);

		// l'attribut d�finit la valeur des champs que l'on veut fetcher
		xmlPars.getNodFromAttribute("telephone", "email");
		// on r�cup�re un tableau
		resultMail = xmlPars.getEmail();
		resultTel = xmlPars.getTelephone();

	}

	public void messageModeChoser() {

		if (radioSms.isSelected()) {
			// envoyer SMS
		} else if (radioEmail.isChecked()) {
			int taille = resultMail.size();

			String[] toSend = new String[taille];

			for (int i = 0; i < taille; i++) {
				toSend[i] = resultMail.get(i);
			}
			sendEmail(toSend);

		} else {
			// envoyer mail + SMS
		}
	}

}
